package io.khasang.freefly.dao;

import io.khasang.freefly.entity.Cat;

import java.util.List;

public interface BasicDao<T> {
    /**
     * method for add entity
     *
     * @param entity - new entity for creation
     * @return created entity
     *
     */

    T create(T entity);

    /**
     * method for getting entity by specific id
     *
     * @param id - entity's id
     *          @return cat by id
     */
    T getById(long id);

    /**
     * method for getting all data from entity
     *
     * @return data list from entity
     */
    List<T> getList();
}
