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

    /**
     * method for update cat
     *
     * @param cat new date cat
     * @return updated cat, null if not exists cat: cat.id==id
     */
    Cat updateCat(Cat cat);

    /**
     * method for delete cat
     *
     * @param id cat's for delete
     * @return deleted cat
     */
    Cat delCatById(long id);
}
