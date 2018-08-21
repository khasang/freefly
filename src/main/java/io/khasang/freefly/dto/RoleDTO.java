package io.khasang.freefly.dto;

import io.khasang.freefly.entity.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleDTO {
    private Long id;
    private String name;


    public List<RoleDTO> getRoleDTOList(List<Role> roleList) {
        List<RoleDTO> roleDTOList = new ArrayList<>();

        for (Role role : roleList) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(role.getId());
            roleDTO.setName(role.getName());
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;
    }

    public RoleDTO getRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        List<UserDTO> userDTOList = new ArrayList<>();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());

        return roleDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
