package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.RoleDao;
import io.khasang.freefly.entity.Role;

public class RoleDaoImpl extends BasicDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl(Class<Role> entityClass) {
        super(entityClass);
    }
}
