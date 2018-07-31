package io.khasang.freefly.dao;

import java.util.List;

public interface BasicDao<T> {

    /**
     * method for add entity
     *
     * @param entity - new entity for creation
     * @return created entity
     */
    T create(T entity);

    /**
     * method for get entity
     *
     * @param id - entity's id
     * @return entity by id
     */
    T getById(long id);


    /**
     * method for getting data from all entities
     *
     * @return data list from entity
     */
    List<T> getList();
}
