/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataControllerCore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * abstract controller to manage data in sql data provider
 *
 * @author u27brvz04
 * @param <E> type of entity of data object
 * @param <K> type of key value
 */
public abstract class AbstractController<E, K> {

    private Connection connection;
    private ConnectionPool connectionPool;

    /**
     * abstract controller to manage data in sql data provider connection gets
     * in ConnectionPool by connectionPool.getConnection();
     *
     * @throws SQLException
     */
    public AbstractController() throws SQLException {
        try {
            connectionPool = ConnectionPool.getConnectionPool();
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new SQLException("Помилка при створенні контроллера обробки даних </br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        }
    }

    /**
     * useful in query strings to get table name, contained in some constant
     *
     * @return table name
     */
    public abstract String getTableName();

    /**
     * returns current connection in pool by calling
     * connectionPool.returnConnection(connection) method
     *
     * @throws SQLException
     */
    public void returnConnectionInPool() throws SQLException {
        connectionPool.returnConnection(this.connection);
    }

    /**
     * gets new instance of PreparedStatement by calling
     * connection.prepareStatement(sql);
     *
     * @param sql sql-query
     * @return instance of PreparedStatement
     * @throws SQLException
     */
    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new SQLException("Помилка при створенні PrepareStatement для sql-запиту </br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        }
        return ps;
    }

    /**
     * gets new instance of NamedParameterStatement by calling new
     * NamedParameterStatement(connection, sql);
     *
     * @param sql sql-query
     * @return instance of PreparedStatement
     * @throws SQLException
     */
    public NamedParameterStatement getNamedParameterStatement(String sql) throws SQLException {
        NamedParameterStatement nps = null;
        try {
            nps = new NamedParameterStatement(connection, sql);
        } catch (SQLException e) {
            throw new SQLException("Помилка при створенні NamedParameterStatement для sql-запиту </br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        }
        return nps;
    }

    /**
     * closes PreparedStatement from parameter
     *
     * @param ps PreparedStatement to close
     */
    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Закрытие PrepareStatement
    /**
     * closes NamedParameterStatement from parameter
     *
     * @param nps NamedParameterStatement to close
     */
    public void closeNamedParameterStatement(NamedParameterStatement nps) {
        if (nps != null) {
            try {
                nps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * counts total rows in table, table name gets with this.getTableName()
     *
     * @return total row count
     * @throws SQLException
     */
    public Integer getTotalRowCount() throws SQLException {
        Integer totalRowCount;
        String query
                = "select public.sp_get_total_row_count(?) as cnt;";
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ps.setString(1, this.getTableName());
            ResultSet rs = ps.executeQuery();
            rs.next();
            totalRowCount = Integer.parseInt(rs.getString("cnt"));
        } catch (SQLException e) {
            throw new SQLException("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
        return totalRowCount;
    }
}
