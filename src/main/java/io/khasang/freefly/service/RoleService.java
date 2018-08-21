package io.khasang.freefly.service;

import io.khasang.freefly.dto.RoleDTO;
import io.khasang.freefly.entity.Role;

import java.util.List;

public interface RoleService {
    /**
     * method for add role
     *
     * @param role -  new role for creation
     * @return created role
     */
    Role addRole(Role role);

    /**
     * method for getting role by specific id
     *
     * @param id - role's id
     * @return role by id
     */
    RoleDTO getRoleDTOById(long id);

    /**
     * method for getting all roles
     *
     * @return role's list
     */
    List<RoleDTO> getAllRoleDTO();
}
