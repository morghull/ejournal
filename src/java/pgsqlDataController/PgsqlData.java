/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgsqlDataController;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author u27brvz04
 */
public class PgsqlData {

    private static final String URL = "jdbc:postgresql://ant:5432/antdb";
    private static final String USERNAME = "iskrasystems";
    private static final String PASSWORD = "whrahz5q";

    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    public static Date loadingDate = null;
    //static List<String> columnsList = null;
    //static List<Map<String, String>> result = null;

    public PgsqlData() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getPageRows(String tableName, int rowsOnPage, int pageNumber, Map<String, String> filterConditionMap) {
        try {
            String queryString = "";
            String queryStringLoadingDate = "";
            String whereStatementString = "";
            int parameterIndex = 0;
            List<String> validFilersList = new ArrayList<>();
            for (Map.Entry<String, String> mapEntry : filterConditionMap.entrySet()) {
                if (mapEntry.getKey().equals("osd")) {
                    validFilersList.add(mapEntry.getKey());
                    whereStatementString += (whereStatementString.equals("") ? " where" : " and") + " osd_c||osd_r=?";
                } else if (mapEntry.getKey().equals("osdch")) {
                    validFilersList.add(mapEntry.getKey());
                    whereStatementString += (whereStatementString.equals("") ? " where" : " and") + " osdch_c||osdch_r=?";
                } else if (mapEntry.getKey().equals("osdk")) {
                    validFilersList.add(mapEntry.getKey());
                    whereStatementString += (whereStatementString.equals("") ? " where" : " and") + " osdk_c||osdk_r=?";
                } else if (mapEntry.getKey().equals("kiz")) {
                    validFilersList.add(mapEntry.getKey());
                    whereStatementString += (whereStatementString.equals("") ? " where" : " and") + " kiz=?";
                } else if (mapEntry.getKey().equals("km")) {
                    validFilersList.add(mapEntry.getKey());
                    whereStatementString += (whereStatementString.equals("") ? " where" : " and") + " km=?";
                } else if (mapEntry.getKey().equals("kp")) {
                    validFilersList.add(mapEntry.getKey());
                    whereStatementString += (whereStatementString.equals("") ? " where" : " and") + " kp=?";
                } else if (mapEntry.getKey().equals("nc")) {
                    validFilersList.add(mapEntry.getKey());
                    whereStatementString += (whereStatementString.equals("") ? " where" : " and") + " trim(nc)=?";
                } else if (mapEntry.getKey().equals("nzak")) {
                    validFilersList.add(mapEntry.getKey());
                    whereStatementString += (whereStatementString.equals("") ? " where" : " and") + " nzak=?";
                }
            }
            if (tableName.equals("tmp")) {
                queryString = "select row_number()"
                        + " over(order by osdch_t,osdch_r,osdch_c,osdk_t,osdk_r,osdk_c,svi) as numb,"
                        + "trim(osdch_c||osdch_r) as osdch,trim(osdk_c||osdk_r) as osdk,svi,cp,tmpsql.nizv as nizv,"
                        + "coalesce(naimch.naim,'') as naimch,coalesce(naimk.naim,'') as naimk"
                        + " from clippersql.tmpsql left join clippersql.naimesql naimch on"
                        + " (osd_t=osdch_t and osd_c=osdch_c and osd_r=osdch_r)"
                        + " left join clippersql.naimesql naimk on"
                        + " (naimk.osd_t=osdk_t and naimk.osd_c=osdk_c and naimk.osd_r=osdk_r)"
                        + whereStatementString + " offset ? limit ?;";
                queryStringLoadingDate = "select datez from clippersql.tmpsql limit 1;";
            } else if (tableName.equals("tmi")) {
                queryString = "select row_number() over(order by osdch_t,osdch_r,osdch_c,svi) as numb,"
                        + "trim(osdch_c||osdch_r) as osdch,svi,nc,tmisql.nizv as nizv,"
                        + "coalesce(naim,'') as naim"
                        + " from clippersql.tmisql left join clippersql.naimesql on"
                        + "(osd_t=osdch_t and osd_c=osdch_c and osd_r=osdch_r)"
                        + whereStatementString + " offset ? limit ?;";
                queryStringLoadingDate = "select datez from clippersql.tmisql limit 1;";
            } else if (tableName.equals("mm76030")) {
                queryString = "select row_number() over(order by kiz,km,osd_t,osd_r,osd_c,nc,svi) as numb,"
                        + "kiz,trim(osd_c||osd_r) as osd,km,nc,"
                        + "md,normd,massa,normi,itog,svi,coalesce(naim,'') as naim"
                        + " from clippersql.mm76030sql left join clippersql.naimesql using(osd_t,osd_c,osd_r)"
                        + whereStatementString + " offset ? limit ?;";
                queryStringLoadingDate = "select datez from clippersql.mm76030sql limit 1;";
            } else if (tableName.equals("naime")) {
                queryString = "select row_number() over(order by osd_t,osd_r,osd_c) as numb,"
                        + "trim(osd_c||osd_r) as osd,naim,nizv"
                        + " from clippersql.naimesql"
                        + whereStatementString + " offset ? limit ?;";
                queryStringLoadingDate = "select datez from clippersql.naimesql limit 1;";
            } else if (tableName.equals("kcm")) {
                queryString = "select distinct on (km) dense_rank() over(order by km) as numb,km,nm,ein,ns,json"
                        + " from ("
                        + "select km,ein,ns,nm from clippersql.kcmsql order by km,svi desc"
                        + ") as desc_sort"
                        + " left join ("
                        + "select km,array_to_json(array_agg(foo)) as json from ("
                        + "select km,svi,cn,ocn,cuch,ocuch,cp,ocp,prim,nm,ein,ns,buro from clippersql.kcmsql order by km,svi"
                        + ") as foo group by km"
                        + ") as foo using (km)"
                        + whereStatementString + " offset ? limit ?;";
                queryStringLoadingDate = "select datez from clippersql.kcmsql limit 1;";
            } else if (tableName.equals("ncpki")) {
                queryString = "set enable_seqscan=on;"
                        + "select distinct on (kp) dense_rank() over(order by kp) as numb,kp,np,od,ei,ns,json"
                        + " from ("
                        + "select kp,np,od,ei,ns from clippersql.ncpkisql order by kp,svi desc"
                        + ") as desc_sort"
                        + " left join ("
                        + "select kp,array_to_json(array_agg(foo)) as json from ("
                        + "select kp,svi,cpn,ocn,cpuch,ocuch,cp,ocp,prim,np,od,ei,ns,podr from clippersql.ncpkisql order by kp,svi"
                        + ") as foo group by kp"
                        + ") as foo using (kp)"
                        + whereStatementString + " offset ? limit ?;";
                queryStringLoadingDate = "select datez from clippersql.ncpkisql limit 1;";
            } else if (tableName.equals("ski")) {
                queryString = "select row_number() over(order by kiz) as numb,"
                        + "nzak,trim(osd_c||osd_r) as osd,kiz,pri,nvp,naim,nizv"
                        + " from clippersql.skisql"
                        + whereStatementString + " offset ? limit ?;";
                queryStringLoadingDate = "select datez from clippersql.skisql limit 1;";
            }
            int startPosition = (pageNumber - 1) * rowsOnPage;
            preparedStatement = connection.prepareStatement(queryString);

            for (String i : validFilersList) {
                preparedStatement.setString(++parameterIndex, filterConditionMap.get(i).trim());
            }
            preparedStatement.setInt(++parameterIndex, startPosition);
            preparedStatement.setInt(++parameterIndex, rowsOnPage);
            resultSet = preparedStatement.executeQuery();
            if (!queryStringLoadingDate.equals("")) {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(queryStringLoadingDate);
                while (rs.next()) {
                    loadingDate = rs.getDate("datez");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    /* ignore */
                }
            }
        }
        return resultSet;
    }

    public ResultSet getRows(String tableName) {
        try {
            String queryString = "";
            if (tableName.equals("software")) {
                queryString = "select row_number() over (order by softname)"
                        + "as numb,softname,sysname,schemaname,softid from brvz.software;";
            }
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    /* ignore */
                }
            }
        }
        return resultSet;
    }

    public ResultSet getRows(String tableName, Map<String, String> filterConditionMap) {
        try {
            String queryString = "";
            String whereStatementString = "";
            int parameterIndex = 0;
            List<String> validFilersList = new ArrayList<>();
            Map<String, String> columnTypes = new HashMap<String, String>();
            for (Map.Entry<String, String> mapEntry : filterConditionMap.entrySet()) {
                if (mapEntry.getKey().equals("softid")) {
                    validFilersList.add(mapEntry.getKey());
                    whereStatementString += (whereStatementString.equals("") ? " where" : " and") + " softid=?";
                    columnTypes.put("softid", "numeric");
                }
            }
            if (tableName.equals("software")) {
                queryString = "select softname,sysname,schemaname,softid from brvz.software"
                        + whereStatementString + ";";
            }
            preparedStatement = connection.prepareStatement(queryString);

            for (String i : validFilersList) {
                if (columnTypes.get(i).trim().equals("string")) {
                    preparedStatement.setString(++parameterIndex, filterConditionMap.get(i).trim());
                } else if (columnTypes.get(i).trim().equals("numeric")) {
                    preparedStatement.setInt(++parameterIndex, Integer.parseInt(filterConditionMap.get(i).trim()));
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    /* ignore */
                }
            }
        }
        return resultSet;
    }

    public String insertRow(String tableName, Map<String, String> rowDataMap) throws SQLException {
        String newId = "";
        try {
            String queryString;
            String fullTableNameString = "";
            String retrievedIdColumnName = "";
            String columnsInsertionString = "";
            String valuesInsertionString = "";
            int parameterIndex = 0;
            List<String> columnsInsertionList = new ArrayList<>();
            Map<String, String> columnTypes = new HashMap<String, String>();

            if (tableName.equals("software")) {
                columnTypes.putAll(new HashMap<String, String>() {
                    {
                        put("softid", "numeric");
                        put("softname", "string");
                        put("sysname", "string");
                        put("schemaname", "string");
                    }
                });
                columnsInsertionList = Arrays.asList("softname", "sysname", "schemaname");
                fullTableNameString = "brvz.software";
                retrievedIdColumnName = "softid";
            }
            for (String i : columnsInsertionList) {
                columnsInsertionString += (columnsInsertionString.equals("") ? "" : ",") + i;
                valuesInsertionString += (valuesInsertionString.equals("") ? "" : ",") + "?";
            }
            queryString = "insert into " + fullTableNameString
                    + " (" + columnsInsertionString
                    + ") values (" + valuesInsertionString
                    + ") returning " + retrievedIdColumnName + ";";

            preparedStatement = connection.prepareStatement(queryString);

            for (String i : columnsInsertionList) {
                if (columnTypes.get(i).trim().equals("string")) {
                    preparedStatement.setString(++parameterIndex, rowDataMap.get(i).trim());
                } else if (columnTypes.get(i).trim().equals("numeric")) {
                    preparedStatement.setInt(++parameterIndex, Integer.parseInt(rowDataMap.get(i).trim()));
                }
            }

            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            newId = resultSet.getString(retrievedIdColumnName);

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    /* ignore */
                }
            }
        }
        return newId;
    }

    public String updateRow(String tableName, Map<String, String> rowDataMap) throws SQLException {
        String id = "";
        try {
            String queryString;
            String fullTableNameString = "";
            String columnsUpdatingString = "";
            String whereStatementString = "";
            int parameterIndex = 0;
            List<String> columnsUpdatingList = new ArrayList<>();
            List<String> columnsWhereStatementList = new ArrayList<>();
            Map<String, String> columnTypes = new HashMap<String, String>();

            if (tableName.equals("software")) {
                columnTypes.putAll(new HashMap<String, String>() {
                    {
                        put("softid", "numeric");
                        put("softname", "string");
                        put("sysname", "string");
                        put("schemaname", "string");
                    }
                });
                columnsUpdatingList = Arrays.asList("softname", "sysname", "schemaname");
                columnsWhereStatementList = Arrays.asList("softid");
                fullTableNameString = "brvz.software";
                id = rowDataMap.get("softid").trim();
            }
            for (String i : columnsUpdatingList) {
                columnsUpdatingString += (columnsUpdatingString.equals("") ? "" : ",") + i + "=?";
            }
            for (String i : columnsWhereStatementList) {
                whereStatementString += (whereStatementString.equals("") ? "" : " and ") + i + "=?";
            }
            queryString = "update " + fullTableNameString + " set "
                    + columnsUpdatingString
                    + " where " + whereStatementString + ";";

            preparedStatement = connection.prepareStatement(queryString);

            for (String i : columnsUpdatingList) {
                if (columnTypes.get(i).trim().equals("string")) {
                    preparedStatement.setString(++parameterIndex, rowDataMap.get(i).trim());
                } else if (columnTypes.get(i).trim().equals("numeric")) {
                    preparedStatement.setInt(++parameterIndex, Integer.parseInt(rowDataMap.get(i).trim()));
                }
            }
            for (String i : columnsWhereStatementList) {
                if (columnTypes.get(i).trim().equals("string")) {
                    preparedStatement.setString(++parameterIndex, rowDataMap.get(i).trim());
                } else if (columnTypes.get(i).trim().equals("numeric")) {
                    preparedStatement.setInt(++parameterIndex, Integer.parseInt(rowDataMap.get(i).trim()));
                }
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    /* ignore */
                }
            }
        }
        return id;
    }

    public void deleteRow(String tableName, Map<String, String> rowDataMap) throws SQLException {
        try {
            String queryString;
            String fullTableNameString = "";
            String whereStatementString = "";
            int parameterIndex = 0;
            List<String> columnsWhereStatementList = new ArrayList<>();
            Map<String, String> columnTypes = new HashMap<String, String>();

            if (tableName.equals("software")) {
                columnTypes.putAll(new HashMap<String, String>() {
                    {
                        put("softid", "numeric");
                        put("softname", "string");
                        put("sysname", "string");
                        put("schemaname", "string");
                    }
                });
                columnsWhereStatementList = Arrays.asList("softid");
                fullTableNameString = "brvz.software";
            }
            for (String i : columnsWhereStatementList) {
                whereStatementString += (whereStatementString.equals("") ? "" : " and ") + i + "=?";
            }
            queryString = "delete from " + fullTableNameString
                    + " where " + whereStatementString + ";";
            preparedStatement = connection.prepareStatement(queryString);

            for (String i : columnsWhereStatementList) {
                if (columnTypes.get(i).trim().equals("string")) {
                    preparedStatement.setString(++parameterIndex, rowDataMap.get(i).trim());
                } else if (columnTypes.get(i).trim().equals("numeric")) {
                    preparedStatement.setInt(++parameterIndex, Integer.parseInt(rowDataMap.get(i).trim()));
                }
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    /* ignore */
                }
            }
        }
    }
}
