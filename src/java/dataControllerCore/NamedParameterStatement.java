/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataControllerCore;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author u27brvz04
 */
public class NamedParameterStatement {

    private final PreparedStatement preparedStatement;
    private List<String> fields = new ArrayList<String>();

    /**
     * @param connection the database connection
     * @param query the parameterized query
     * @throws SQLException if the statement could not be created
     */
    public NamedParameterStatement(Connection connection, String query) throws SQLException {
        fields = new ArrayList<>();
        //another one regexp for matcher "(?<!')(@[\\w]*)(?!')"
        Pattern findParametersPattern = Pattern.compile("(?!\\B'[^']*)(@\\w+)(?![^']*'\\B)");
        Matcher matcher = findParametersPattern.matcher(query);
        while (matcher.find()) {
            fields.add(matcher.group().substring(1));
        }
        preparedStatement = connection.prepareStatement(query.replaceAll(findParametersPattern.pattern(), "?"));
    }

    public void setString(String name, String value) throws SQLException {
        preparedStatement.setString(getIndex(name), value);
    }

    public void setDate(String name, Date value) throws SQLException {
        preparedStatement.setDate(getIndex(name), value);
    }

    public void setInt(String name, int value) throws SQLException {
        preparedStatement.setInt(getIndex(name), value);
    }

    private int getIndex(String name) {
        return fields.indexOf(name) + 1;
    }

    public ResultSet executeQuery() throws SQLException {
        return preparedStatement.executeQuery();
    }

    public int executeUpdate() throws SQLException {
        return preparedStatement.executeUpdate();
    }

    public void close() throws SQLException {
        preparedStatement.close();
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
}
