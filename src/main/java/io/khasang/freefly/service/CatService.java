package io.khasang.freefly.service;

import io.khasang.freefly.entity.Cat;

import java.util.List;

public interface CatService {

    /**
     * method for add cat
     *
     * @param cat new cat for creation
     * @return created cat
     */
    Cat addCat(Cat cat);

    /**
     * method for get cat by specific id
     *
     * @param id id cat's
     * @return cat : cat's id = id
     */
    Cat getCatById(long id);

    /**
     * method for get all cats
     *
     * @return all cats
     */
    List<Cat> getList();
}
