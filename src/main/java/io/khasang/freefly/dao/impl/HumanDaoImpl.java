package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.HumanDao;
import io.khasang.freefly.entity.Human;

public class HumanDaoImpl extends BasicDaoImpl<Human> implements HumanDao {
    public HumanDaoImpl(Class<Human> entityClass) {
        super(entityClass);
    }
}
