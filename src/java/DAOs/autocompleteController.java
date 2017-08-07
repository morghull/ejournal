/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import dataControllerCore.AbstractController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author u27brvz04
 */
public class autocompleteController extends AbstractController {

    private final static String AUTOCOMPLETE_LIMIT = "7";

    private final String tableName;
    private final String fieldName;

    /**
     * controller to manage data for autocomplete
     *
     * @param tableName table name to search in data for autocomplete, this
     * parameter should contain full table name with schema name like:
     * tablename.schemaname
     * @param fieldName field name in table to search data for autocomplete
     * @throws SQLException
     */
    public autocompleteController(String tableName, String fieldName) throws SQLException {
        this.tableName = tableName.toLowerCase();
        this.fieldName = fieldName.toLowerCase();
    }

    /**
     * gets values tableName.fieldName that matched part+"%" clause
     *
     * @param pattern part of string to search in tableName.fieldName to get
     * autocomplete list
     * @return returns list of strings with tableName.fieldName values
     * max:AUTOCOMPLETE_LIMIT
     * @throws SQLException
     */
    public List<String> getAutocompleteByPart(String pattern) throws SQLException {
        List<String> lst = new LinkedList<String>();
        String query;
        String type = "like"; //in some cases it uses regexp matching
        if (tableName.equals("clippersql.mm76030sql") && fieldName.equals("osd")) {
            query = "select distinct osd_t,osd_r,osd_c,osd_c||osd_r as atc_fld"
                    + " from clippersql.mm76030sql"
                    + " where osd_c||osd_r like ? order by osd_t,osd_r,osd_c limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("clippersql.mm76030sql") && fieldName.equals("kiz")) {
            query = "select distinct kiz as atc_fld"
                    + " from clippersql.mm76030sql"
                    + " where kiz like ? order by kiz limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("clippersql.mm76030sql") && fieldName.equals("km")) {
            query = "select distinct km as atc_fld"
                    + " from clippersql.mm76030sql"
                    + " where km like ? order by km limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("clippersql.mm76030sql") && fieldName.equals("nc")) {
            query = "select distinct nc as atc_fld"
                    + " from clippersql.mm76030sql"
                    + " where trim(nc) like ? order by nc limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("clippersql.naimesql") && fieldName.equals("osd")) {
            query = "select osd_c||osd_r as atc_fld"
                    + " from clippersql.naimesql"
                    + " where osd_c||osd_r like ? order by osd_t,osd_r,osd_c limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("clippersql.tmisql") && fieldName.equals("osdch")) {
            query = "select osdch_c||osdch_r as atc_fld"
                    + " from clippersql.tmisql"
                    + " where osdch_c||osdch_r like ? order by osdch_t,osdch_r,osdch_c limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("clippersql.tmpsql") && fieldName.equals("osdch")) {
            query = "select distinct osdch_t,osdch_r,osdch_c,osdch_c||osdch_r as atc_fld"
                    + " from clippersql.tmpsql"
                    + " where osdch_c||osdch_r like ? order by osdch_t,osdch_r,osdch_c limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("clippersql.tmpsql") && fieldName.equals("osdk")) {
            query = "select distinct osdk_t,osdk_r,osdk_c,osdk_c||osdk_r as atc_fld"
                    + " from clippersql.tmpsql"
                    + " where osdk_c||osdk_r like ? order by osdk_t,osdk_r,osdk_c limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("clippersql.skisql") && fieldName.equals("nzak")) {
            query = "select distinct nzak as atc_fld"
                    + " from clippersql.skisql"
                    + " where nzak ~* ? order by nzak limit " + AUTOCOMPLETE_LIMIT + ";";
            type = "regexp";
        } else if (tableName.equals("clippersql.skisql") && fieldName.equals("osd")) {
            query = "select osd_c||osd_r as atc_fld"
                    + " from clippersql.skisql"
                    + " where osd_c||osd_r like ? order by osd_t,osd_r,osd_c limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("clippersql.skisql") && fieldName.equals("kiz")) {
            query = "select kiz as atc_fld"
                    + " from clippersql.skisql"
                    + " where kiz like ? order by kiz limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("clippersql.kcmsql") && fieldName.equals("km")) {
            query = "select distinct km as atc_fld"
                    + " from clippersql.kcmsql"
                    + " where km like ? order by km limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("clippersql.ncpkisql") && fieldName.equals("kp")) {
            query = "select distinct kp as atc_fld"
                    + " from clippersql.ncpkisql"
                    + " where kp like ? order by kp limit " + AUTOCOMPLETE_LIMIT + ";";
        } else if (tableName.equals("zapas.rdt") && fieldName.equals("rdtk")) {
            query = "select rdtk as atc_fld"
                    + " from zapas.rdt"
                    + " where rdtk like ? order by rdtk limit " + AUTOCOMPLETE_LIMIT + ";";
        } else {
            throw new SQLException("Не вірно вказіні параметр назви таблиці або імені поля");
        }
        PreparedStatement ps = getPrepareStatement(query);
        try {
            ps.setString(1, (type.equals("like")) ? pattern + "%" : "^" + pattern);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lst.add(rs.getString("atc_fld"));
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
        return tableName;
    }

}
