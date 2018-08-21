package io.khasang.freefly.controller;

import io.khasang.freefly.dto.RoleDTO;
import io.khasang.freefly.entity.Role;
import io.khasang.freefly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public RoleDTO getRoleById(@PathVariable(value = "id") String id) {
        return roleService.getRoleDTOById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoleDTO();
    }
}
