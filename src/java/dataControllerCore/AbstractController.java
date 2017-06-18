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
 *
 * @author u27brvz04
 */
public abstract class AbstractController<E, K> {

    private Connection connection;
    private ConnectionPool connectionPool;

    public AbstractController() throws SQLException {
        try {
            connectionPool = ConnectionPool.getConnectionPool();
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new SQLException("Помилка при створенні контроллера обробки даних </br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        }
    }

    public abstract String getTableName();

    // Возвращения экземпляра Connection в пул соединений
    public void returnConnectionInPool() throws SQLException {
        connectionPool.returnConnection(this.connection);
    }

    // Получение экземпляра PrepareStatement
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

    // Получение экземпляра NamedParameterStatement
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

    // Закрытие PrepareStatement
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
    public void closeNamedParameterStatement(NamedParameterStatement nps) {
        if (nps != null) {
            try {
                nps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getTotalRowCount() throws SQLException {
        Integer totalRowCount;
        String query
                = "select public.sp_get_total_row_count(?) as cnt;";
        PreparedStatement ps = getPrepareStatement(query);
        ps.setString(1, this.getTableName());
        try {
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
