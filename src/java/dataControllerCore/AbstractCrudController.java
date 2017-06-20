/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataControllerCore;

import java.sql.SQLException;
import java.util.List;

/**
 * controller to implement CRUD functionality in data
 *
 * @author u27brvz04
 * @param <E> type of entity of data object
 * @param <K> type of key value
 */
public abstract class AbstractCrudController<E, K> extends AbstractController {

    /**
     * controller to implement CRUD functionality in data
     *
     * @throws SQLException
     */
    public AbstractCrudController() throws SQLException {
    }

    /**
     * creates new record in data
     *
     * @param entity data object
     * @return key value of new record
     * @throws SQLException
     */
    public abstract K create(E entity) throws SQLException;

    /**
     * updates record in data
     *
     * @param entity data object
     * @throws SQLException
     */
    public abstract void update(E entity) throws SQLException;

    /**
     * deletes record in data
     *
     * @param id key value of record to delete
     * @throws SQLException
     */
    public abstract void delete(K id) throws SQLException;

    /**
     * gets list of instances of all data objects from data source
     *
     * @return list of data object instances
     * @throws SQLException
     */
    public abstract List<E> getAll() throws SQLException;

    /**
     * gets instance of data object by key value
     *
     * @param id key value
     * @return data object instance
     * @throws SQLException
     */
    public abstract E getEntityById(K id) throws SQLException;

    /**
     * gets list of instances of data objects for pagination view
     *
     * @param pageNumber number of page to return
     * @param pageSize current page size
     * @return list of instances of data objects
     * @throws SQLException
     */
    public abstract List<E> getPage(int pageNumber, int pageSize) throws SQLException;

    /**
     * gets number of row with incoming key value in specific ordering of data
     *
     * @param id key value of row to search
     * @return row number
     * @throws SQLException
     */
    public abstract int getRowNumberInOrdering(K id) throws SQLException;

}
