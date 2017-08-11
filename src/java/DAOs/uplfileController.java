/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import dataControllerCore.AbstractCrudController;
import dataObjects.uplfile;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
        uplfile entity;
        String query
                = "select ufid,ufname,ufcontent,idd "
                + "from xxx.uplfiles "
                + "where ufid=? limit 1;";
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = new uplfile();
                entity.setUfid(rs.getInt(1));
                entity.setUfname(rs.getString(2));
                entity.setUfcontent(rs.getBinaryStream(3));
                entity.setIdd(rs.getInt(4));
            } else {
                throw new SQLException("За заданим ідентифікатором відсутній запис");
            }

        } catch (SQLException e) {
            throw new SQLException("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
        return entity;
    }

    public boolean createFromList(List<uplfile> list) throws SQLException {
        String query
                = "insert into " + TABLE_NAME + "(ufname,ufcontent,idd) values";
        int index = 0;
        query += new String(new char[list.size()]).replace("\0", ",(?,?,?)").substring(1);
        PreparedStatement ps = getPrepareStatement(query);
        try {
            for (uplfile entity : list) {
                ps.setString(++index, entity.getUfname());
                ps.setBinaryStream(++index, entity.getUfcontent());
                ps.setInt(++index, entity.getIdd());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Помилка при створенні записів зі списку</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
        return true;
    }

    public boolean updateFromList(List<uplfile> list) throws SQLException {
        String query
                = "delete from " + TABLE_NAME + " where idd=?;insert into " + TABLE_NAME + "(ufname,ufcontent,idd) values";
        int index = 0;
        query += new String(new char[list.size()]).replace("\0", ",(?,?,?)").substring(1);
        PreparedStatement ps = getPrepareStatement(query);
        ps.setInt(++index, list.get(0).getIdd());
        try {
            for (uplfile entity : list) {
                ps.setString(++index, entity.getUfname());
                ps.setBinaryStream(++index, entity.getUfcontent());
                ps.setInt(++index, entity.getIdd());
            }
            ps.executeUpdate();
        } catch (Throwable e) {
            throw new SQLException("Помилка при створенні записів зі списку</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
        return true;
    }

    public List<uplfile> getListByIdd(int idd) throws SQLException {
        List<uplfile> lst = new LinkedList<uplfile>();
        String query
                = "select ufid,ufname,idd"
                + "from " + TABLE_NAME
                + "where idd=?";
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ps.setInt(1, idd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                uplfile entity = new uplfile();
                entity.setUfid(rs.getInt(1));
                entity.setUfname(rs.getString(2));
                entity.setIdd(rs.getInt(3));

                lst.add(entity);
            }
        } catch (SQLException e) {
            throw new SQLException("Помилка при спробі отримати завантажені файли поточного документа</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
        return lst;
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
