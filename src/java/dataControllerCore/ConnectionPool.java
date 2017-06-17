/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataControllerCore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.InitialContext;
import org.apache.tomcat.jdbc.pool.DataSource;

/**
 *
 * @author u27brvz04
 */
public class ConnectionPool {

    private InitialContext initContext;
    private DataSource dataSource;

    public ConnectionPool() {
        try {
            initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/pgAnt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getConnectionPool() {
        return new ConnectionPool();
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public synchronized void returnConnection(Connection c) throws SQLException {
        try {
            if (!c.isClosed()) {
                c.close();
            }
        } catch (SQLException e) {
            throw new SQLException("Помилка при поверненні sql-з'днання у pool</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        }
    }
}
