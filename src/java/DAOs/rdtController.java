/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import dataControllerCore.AbstractCrudController;
import dataObjects.rdt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author u27brvz04
 */
public class rdtController extends AbstractCrudController<rdt, String> {
    
    private static final String TABLE_NAME = "zapas.rdt";
    
    public rdtController() throws SQLException {
    }

    @Override
    public String create(rdt entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(rdt entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<rdt> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public rdt getEntityById(String id) throws SQLException {
        rdt entity;
        String query
                = "select rdtk,rdtn "
                + "from " + TABLE_NAME + " "
                + "where rdtk=? limit 1";
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = new rdt();
                entity.setRdtk(rs.getString(1));
                entity.setRdtn(rs.getString(2));
            } else {
                throw new SQLException("За заданим ідентифікатором відсутній запис","invalid");
            }
            //ejrdok.setUfid(rs.getInt(12));
        } catch (SQLException e) {
            throw new SQLException("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>",e.getSQLState());
        } finally {
            closePrepareStatement(ps);
        }
        return entity;
    }

    @Override
    public List<rdt> getPage(int pageNumber, int pageSize) throws SQLException {
        List<rdt> lst = new LinkedList<rdt>();
        String query
                = "select rdtk,rdtn "
                + "from " + TABLE_NAME + " "
                + "order by rdtk limit " + pageSize + " offset " + pageSize * (pageNumber - 1);
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rdt entity = new rdt();
                entity.setRdtk(rs.getString(1));
                entity.setRdtn(rs.getString(2));

                lst.add(entity);
            }
        } catch (SQLException e) {
            throw new SQLException("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
        return lst;
    }

    @Override
    public int getRowNumberInOrdering(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
    
    public boolean valdiateRdtk(String value) throws SQLException{
        boolean isValid = false;
        String query
                = "select 1 as chk "
                + "from " + TABLE_NAME + " "
                + "where rdtk=? limit 1";
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
