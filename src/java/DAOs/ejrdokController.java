/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import dataControllerCore.AbstractCrudController;
import dataControllerCore.NamedParameterStatement;
import dataObjects.ejrdok;
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
    public void delete(Integer id) throws SQLException {
        String query
                = "delete from " + TABLE_NAME + " where idd=?;";

        PreparedStatement ps = getPrepareStatement(query);

        try {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }
    }

    @Override
    public List<ejrdok> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ejrdok getEntityById(Integer id) throws SQLException {
        ejrdok entity;
        String query
                = "with uplfiles as ("
                + "      select ufid,ufname,idd"
                + "      from xxx.uplfiles"
                + "      where idd=?"
                + "      order by ufname"
                + "      ),"
                + "     uf_agg as ("
                + "      select idd,json_agg(row_to_json(uf)) as json"
                + "      from uplfiles uf"
                + "      group by idd"
                + "     )"
                + "select idd,rdk,rdn,rdd,nazz,rdsh,ordk,ordn,ordd,dvd,nzak,prim,json "
                + "from " + TABLE_NAME + " "
                + "left join uf_agg using(idd) "
                + "where idd=? limit 1";
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ps.setInt(1, id);
            ps.setInt(2, id);
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
                entity.setFilesjson(rs.getString(13));
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

    @Override
    public List<ejrdok> getPage(int pageNumber, int pageSize) throws SQLException {
        List<ejrdok> lst = new LinkedList<ejrdok>();
        String query
                = "with uplfiles as ("
                + "      select ufid,ufname,idd"
                + "      from xxx.uplfiles"
                + "      order by idd,ufname"
                + "      ),"
                + "     uf_agg as ("
                + "      select idd,json_agg(row_to_json(uf)) as json"
                + "      from uplfiles uf"
                + "      group by idd"
                + "     )"
                + "select idd,rdk,rdn,rdd,nazz,rdsh,ordk,ordn,ordd,dvd,nzak,prim,json "
                + "from " + TABLE_NAME + " "
                + "   left join uf_agg using(idd)"
                + "order by rdd,rdk,rdn,nzak limit " + pageSize + " offset "
                + pageSize * ((pageNumber == 0) ? 0 : (pageNumber - 1));
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
                entity.setFilesjson(rs.getString(13));

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

    @Override
    public int getRowNumberInOrdering(Integer id) throws SQLException {
        String query
                = "with src as ("
                + "   select row_number() over(order by rdd,rdk,rdn,nzak) as rn,idd"
                + "   from " + TABLE_NAME
                + "   )"
                + "select rn "
                + "from src "
                + "where idd=?;";

        ResultSet rs = null;
        int rowNumber;

        PreparedStatement ps = getPrepareStatement(query);

        try {
            ps.setInt(1, id);

            rs = ps.executeQuery();
            rs.next();
            rowNumber = Integer.parseInt(rs.getString("rn"));
        } catch (SQLException e) {
            throw new SQLException("Помилка при виконанні SQL-запиту</br>"
                    + "<div class=\"nested-error\">" + e.getMessage() + "</div>");
        } finally {
            closePrepareStatement(ps);
        }

        return rowNumber;
    }
}
