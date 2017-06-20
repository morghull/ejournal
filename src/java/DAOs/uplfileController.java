/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import dataControllerCore.AbstractCrudController;
import dataObjects.uplfile;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author u27brvz04
 */
public class uplfileController extends AbstractCrudController<uplfile, Integer> {

    private static final String TABLE_NAME = "xxx.uplfiles";
    
    public uplfileController() throws SQLException {
    }
    
    @Override
    public Integer create(uplfile entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(uplfile entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<uplfile> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public uplfile getEntityById(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean createFromList(List<uplfile> list) throws SQLException {
        String query
                = "insert into " + TABLE_NAME + "(ufname,ufcontent,idd) values";
        for (int i = 0; i < list.size(); i++) {
            query += "(?,?,?)";
        }
        PreparedStatement ps = getPrepareStatement(query);
        try {
            for (uplfile entity : list) {
                ps.setString(1, entity.getUfname());
                ps.setBinaryStream(2, entity.getUfcontent());
                ps.setInt(3, entity.getIdd());
            }
        } catch (SQLException e) {
            throw new SQLException("Помилка при створенні записів зі списку</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
        return false;
    }

    @Override
    public List<uplfile> getPage(int pageNumber, int pageSize) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getTotalRowCount() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public int getRowNumberInOrdering(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
