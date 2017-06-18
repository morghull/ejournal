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
    public Integer create(ejrdok entity) throws SQLException {
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
        } catch (SQLException e) {
            throw new SQLException("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
        return newId;
    }

    @Override
    public void update(ejrdok entity) throws SQLException {
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

            nps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closeNamedParameterStatement(nps);
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        return false;
    }

    @Override
    public List<ejrdok> getAll() throws SQLException {
        List<ejrdok> lst = new LinkedList<>();
        String query
                = "select idd,rdk,rdn,rdd,nazz,rdsh,ordk,ordn,ordd,dvd,nzak,prim "
                + "from " + TABLE_NAME + " "
                + "order by rdd,rdk,rdn,nzak";
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ejrdok entity = new ejrdok();
                entity.setIdd(rs.getInt(1));
                entity.setRdk(rs.getString(2));
                entity.setRdn(rs.getString(3));
                entity.setRdd(rs.getDate(4));
                entity.setNazz(rs.getString(5));
                entity.setRdsh(rs.getString(6));
                entity.setOrdk(rs.getString(7));
                entity.setOrdn(rs.getString(8));
                entity.setOrdd(rs.getDate(9));
                entity.setDvd(rs.getDate(10));
                entity.setNzak(rs.getString(11));
                entity.setPrim(rs.getString(12));
                //ejrdok.setUfid(rs.getInt(12));

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
    public ejrdok getEntityById(Integer id) throws SQLException {
        ejrdok entity;
        String query
                = "select idd,rdk,rdn,rdd,nazz,rdsh,ordk,ordn,ordd,dvd,nzak,prim "
                + "from " + TABLE_NAME + " "
                + "where idd=? limit 1";
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = new ejrdok();
                entity.setIdd(rs.getInt(1));
                entity.setRdk(rs.getString(2));
                entity.setRdn(rs.getString(3));
                entity.setRdd(rs.getDate(4));
                entity.setNazz(rs.getString(5));
                entity.setRdsh(rs.getString(6));
                entity.setOrdk(rs.getString(7));
                entity.setOrdn(rs.getString(8));
                entity.setOrdd(rs.getDate(9));
                entity.setDvd(rs.getDate(10));
                entity.setNzak(rs.getString(11));
                entity.setPrim(rs.getString(12));
            } else {
                throw new SQLException("За заданим ідентифікатором відсутній запис");
            }
            //ejrdok.setUfid(rs.getInt(12));
        } catch (SQLException e) {
            throw new SQLException("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
        return entity;
    }

    @Override
    public List<ejrdok> getPage(int pageNumber, int pageSize) throws SQLException {
        List<ejrdok> lst = new LinkedList<>();
        String query
                = "select idd,rdk,rdn,rdd,nazz,rdsh,ordk,ordn,ordd,dvd,nzak,prim "
                + "from " + TABLE_NAME + " "
                + "order by rdd,rdk,rdn,nzak limit " + pageSize + " offset " + pageSize * (pageNumber - 1);
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ejrdok entity = new ejrdok();
                entity.setIdd(rs.getInt(1));
                entity.setRdk(rs.getString(2));
                entity.setRdn(rs.getString(3));
                entity.setRdd(rs.getDate(4));
                entity.setNazz(rs.getString(5));
                entity.setRdsh(rs.getString(6));
                entity.setOrdk(rs.getString(7));
                entity.setOrdn(rs.getString(8));
                entity.setOrdd(rs.getDate(9));
                entity.setDvd(rs.getDate(10));
                entity.setNzak(rs.getString(11));
                entity.setPrim(rs.getString(12));
                //ejrdok.setUfid(rs.getInt(12));

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
    public String getTableName() {
        return TABLE_NAME;
    }
}
