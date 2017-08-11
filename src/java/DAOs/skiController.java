/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import dataControllerCore.AbstractCrudController;
import dataObjects.ski;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author u27brvz04
 */
public class skiController extends AbstractCrudController<ski, String> {
    
    private static final String TABLE_NAME = "clippersql.skisql";

    public skiController() throws SQLException {
    }

    @Override
    public String create(ski entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ski entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ski> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ski getEntityById(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ski> getPage(int pageNumber, int pageSize) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowNumberInOrdering(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
    
    public boolean valdiateNzak(String value) throws SQLException{
        boolean isValid = false;
        String query
                = "select 1 as chk "
                + "from " + TABLE_NAME + " "
                + "where nzak=? limit 1";
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            isValid = rs.isBeforeFirst();
        } catch (SQLException e) {
            throw new SQLException("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>",e.getSQLState());
        } finally {
            closePrepareStatement(ps);
        }
        return isValid;
    }
}
