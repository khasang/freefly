package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.UserDao;
import io.khasang.freefly.entity.User;

public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    public UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }
}
