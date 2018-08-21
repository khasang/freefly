package io.khasang.freefly.dao;

import io.khasang.freefly.entity.Cat;

import java.util.List;

public interface CatDao extends BasicDao<Cat>{

    List<Cat> getCatsByName(String name);
}
