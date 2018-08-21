package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.RoleDao;
import io.khasang.freefly.dto.RoleDTO;
import io.khasang.freefly.entity.Role;
import io.khasang.freefly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleDTO roleDTO;

    @Override
    public Role addRole(Role role) {
        return roleDao.create(role);
    }

    @Override
    public RoleDTO getRoleDTOById(long id) {
        return roleDTO.getRoleDTO(roleDao.getById(id));
    }

    @Override
    public List<RoleDTO> getAllRoleDTO() {
        return roleDTO.getRoleDTOList(roleDao.getList());
    }
}