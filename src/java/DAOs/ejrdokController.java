/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import dataControllerCore.AbstractCrudController;
import dataControllerCore.NamedParameterStatement;
import dataObjects.ejrdok;
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
public class ejrdokController extends AbstractCrudController<ejrdok, Integer> {

    private static final String TABLE_NAME = "xxx.ejrdok";

    public ejrdokController() throws SQLException {
    }

    @Override
    public Integer create(ejrdok entity) throws Exception {
        String query
                = "insert into " + TABLE_NAME + "(rdk,rdn,rdd,nazz,rdsh,ordk,ordn,ordd,dvd,nzak,prim)"
                + "values (?,?,?,?,?,?,?,?,?,?,?) returning idd";

        ResultSet rs = null;
        int newId;

        PreparedStatement ps = getPrepareStatement(query);

        try {
            ps.setString(1, entity.getRdk());
            ps.setString(2, entity.getRdn());
            ps.setDate(3, new java.sql.Date(entity.getRdd().getTime()));
            ps.setString(4, entity.getNazz());
            ps.setString(5, entity.getRdsh());
            ps.setString(6, entity.getOrdk());
            ps.setString(7, entity.getOrdn());
            ps.setDate(8, new java.sql.Date(entity.getOrdd().getTime()));
            ps.setDate(9, new java.sql.Date(entity.getDvd().getTime()));
            ps.setString(10, entity.getNzak());
            ps.setString(11, entity.getPrim());

            rs = ps.executeQuery();
            rs.next();
            newId = Integer.parseInt(rs.getString("idd"));
        } catch (Exception e) {
            throw new Exception("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
        return newId;
    }

    @Override
    public void update(ejrdok entity) throws Exception {
        String query
                = "update " + TABLE_NAME + " set rdk=@rdk,rdn=@rdn,rdd=@rdd,"
                + "nazz=@nazz,rdsh=@rdsh,ordk=@ordk,ordn=@ordn,ordd=@ordd,"
                + "dvd=@dvd,nzak=@nzak,prim=@prim "
                + "where idd=@idd";

        NamedParameterStatement nps = getNamedParameterStatement(query);

        try {
            nps.setInt("idd", entity.getIdd());
            nps.setString("rdk", entity.getRdk());
            nps.setString("rdn", entity.getRdn());
            nps.setDate("rdd", new java.sql.Date(entity.getRdd().getTime()));
            nps.setString("nazz", entity.getNazz());
            nps.setString("rdsh", entity.getRdsh());
            nps.setString("ordk", entity.getOrdk());
            nps.setString("ordn", entity.getOrdn());
            nps.setDate("ordd", new java.sql.Date(entity.getOrdd().getTime()));
            nps.setDate("dvd", new java.sql.Date(entity.getDvd().getTime()));
            nps.setString("nzak", entity.getNzak());
            nps.setString("prim", entity.getPrim());

            nps.executeQuery();
        } catch (Exception e) {
            throw new Exception("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closeNamedParameterStatement(nps);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return false;
    }

    @Override
    public List<ejrdok> getAll() throws Exception {
        List<ejrdok> lst = new LinkedList<>();
        String query
                = "select idd,rdk,rdn,rdd,nazz,rdsh,ordk,ordn,ordd,dvd,nzak,prim "
                + "from " + TABLE_NAME + " "
                + "order by rdd,rdk,rdn,nzak";
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ejrdok ejrdok = new ejrdok();
                ejrdok.setIdd(rs.getInt(1));
                ejrdok.setRdk(rs.getString(2));
                ejrdok.setRdn(rs.getString(3));
                ejrdok.setRdd(rs.getDate(4));
                ejrdok.setNazz(rs.getString(5));
                ejrdok.setRdsh(rs.getString(6));
                ejrdok.setOrdk(rs.getString(7));
                ejrdok.setOrdn(rs.getString(8));
                ejrdok.setOrdd(rs.getDate(9));
                ejrdok.setDvd(rs.getDate(10));
                ejrdok.setNzak(rs.getString(11));
                ejrdok.setPrim(rs.getString(12));
                //ejrdok.setUfid(rs.getInt(12));

                lst.add(ejrdok);
            }
        } catch (SQLException e) {
            throw new Exception("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }

        return lst;
    }

    @Override
    public ejrdok getEntityById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<ejrdok> getPage(int pageNumber, int pageSize) throws Exception {
        List<ejrdok> lst = new LinkedList<>();
        String query
                = "select idd,rdk,rdn,rdd,nazz,rdsh,ordk,ordn,ordd,dvd,nzak,prim "
                + "from " + TABLE_NAME + " "
                + "order by rdd,rdk,rdn,nzak limit " + pageSize + " offset " + pageSize * (pageNumber - 1);
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ejrdok ejrdok = new ejrdok();
                ejrdok.setIdd(rs.getInt(1));
                ejrdok.setRdk(rs.getString(2));
                ejrdok.setRdn(rs.getString(3));
                ejrdok.setRdd(rs.getDate(4));
                ejrdok.setNazz(rs.getString(5));
                ejrdok.setRdsh(rs.getString(6));
                ejrdok.setOrdk(rs.getString(7));
                ejrdok.setOrdn(rs.getString(8));
                ejrdok.setOrdd(rs.getDate(9));
                ejrdok.setDvd(rs.getDate(10));
                ejrdok.setNzak(rs.getString(11));
                ejrdok.setPrim(rs.getString(12));
                //ejrdok.setUfid(rs.getInt(12));

                lst.add(ejrdok);
            }
        } catch (SQLException e) {
            throw new Exception("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
        return lst;
    }

    @Override
    public String getTableName() throws Exception {
        return TABLE_NAME;
    }
}
