package io.khasang.freefly.dao;

import io.khasang.freefly.entity.Role;

import java.util.List;

public interface RoleDao extends BasicDao<Role> {

    /**
     * method for getting role by name
     * @param name
     * @return list roles
     */
    List<Role> getListByName(String name);
}
