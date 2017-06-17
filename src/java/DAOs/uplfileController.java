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

    private static final String TABLE_NAME = "xxx.ejrdok";

    @Override
    public Integer create(uplfile entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(uplfile entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<uplfile> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public uplfile getEntityById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean createFromList(List<uplfile> list) {
        String query
                = "insert into " + TABLE_NAME + "(ufname,ufcontent,idd) values";
        for (int i = 0; i < list.size(); i++) {
            query += "(?,?,?)";
        }
        PreparedStatement ps = getPrepareStatement(query);
        try {
            for (uplfile uplfile : list) {
                ps.setString(1, uplfile.getUfname());
                ps.setBinaryStream(2, uplfile.getUfcontent());
                ps.setInt(3, uplfile.getIdd());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }
        return false;
    }

    @Override
    public List<uplfile> getPage(int pageNumber, int pageSize) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getTotalRowCount() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTableName() throws Exception {
        return TABLE_NAME;
    }

}
