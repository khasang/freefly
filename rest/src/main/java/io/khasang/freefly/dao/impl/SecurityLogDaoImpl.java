package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.SecurityLogDao;
import io.khasang.freefly.entity.SecurityLog;

public class SecurityLogDaoImpl extends BasicDaoImpl<SecurityLog> implements SecurityLogDao {
    public SecurityLogDaoImpl(Class<SecurityLog> entityClass) {
        super(entityClass);
    }
}
