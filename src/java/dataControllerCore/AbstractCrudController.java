/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataControllerCore;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author u27brvz04
 * @param <E> entity type
 * @param <K> key type
 */
public abstract class AbstractCrudController<E, K> extends AbstractController {

    public AbstractCrudController() throws SQLException {
    }

    public abstract K create(E entity) throws SQLException;

    public abstract void update(E entity) throws SQLException;

    public abstract boolean delete(K id) throws SQLException;

    public abstract List<E> getAll() throws SQLException;

    public abstract E getEntityById(K id) throws SQLException;

    public abstract List<E> getPage(int pageNumber, int pageSize) throws SQLException;

    public abstract int getRowNumberInOrdering(K id) throws SQLException;

}
