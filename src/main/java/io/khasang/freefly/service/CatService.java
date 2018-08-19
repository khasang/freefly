package io.khasang.freefly.service;

import io.khasang.freefly.entity.Cat;

import java.util.List;

public interface CatService {

    /**
     * method for add cat
     *
     * @param cat - new cat for creation
     *          @return created cat
     */
    Cat addCat(Cat cat);

    /**
     * method for getting cat by specific id
     *
     * @param id - cat's id
     *          @return cat by id
     */
    Cat getCatById(long id);


    /**
     * method for getting all cats
     *
     * @return cat's list
     */
    List<Cat> getAllCats();
}
