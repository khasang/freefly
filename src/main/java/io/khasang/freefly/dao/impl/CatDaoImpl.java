package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.CatDao;
import io.khasang.freefly.entity.Cat;

public class CatDaoImpl extends BasicDaoImpl<Cat> implements CatDao {
    public CatDaoImpl(Class<Cat> entityClass) {
        super(entityClass);
    }
}
