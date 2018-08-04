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
     * @return entity by id. return null if entity with specific id not exists
     */
    T getById(long id);


    /**
     * method for getting data from all entities
     *
     * @return data list from entity
     */
    List<T> getList();


    /**
     * method for update entity
     *
     * @param entity - new data for entity
     * @return updated entity.
     */
    T update(T entity);

    /**
     * method for delete entity by id
     *
     * @param id entity's for delete
     * @return deleted entity. return null if entity with specific id not exists
     */
    T delById(long id);
}
