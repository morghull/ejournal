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

    public abstract K create(E entity) throws Exception;

    public abstract void update(E entity) throws Exception;

    public abstract boolean delete(K id) throws Exception;

    public abstract List<E> getAll() throws Exception;

    public abstract E getEntityById(K id) throws Exception;

    public abstract List<E> getPage(int pageNumber, int pageSize) throws Exception;

}
