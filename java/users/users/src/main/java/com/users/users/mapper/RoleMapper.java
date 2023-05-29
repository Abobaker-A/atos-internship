package com.users.users.mapper;

import com.users.users.roles.Role;
import com.users.users.roles.RoleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public Role toEntity(RoleDto dto) {
        Role role = new Role();
        role.setName(dto.getName());
        role.setPermissions(dto.getPermissions());
        return role;
    }

    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setPermissions(role.getPermissions());
        return roleDto;
    }
    public List<RoleDto> toDtos(List<Role> roles) {
        return roles.stream().map(this::toDto).collect(Collectors.toList());
    }
}