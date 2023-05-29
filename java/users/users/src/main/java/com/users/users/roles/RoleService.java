package com.users.users.roles;

import com.users.users.exception.MyAppException;
import com.users.users.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private  RoleRepo roleRepo;
    @Autowired
    private RoleMapper roleMapper;

    public RoleDto addRole(RoleDto roleDto) {
        roleDto.setName(roleDto.getName().toUpperCase());
        if (roleRepo.findByName(roleDto.getName()).isPresent()) {
            throw new MyAppException("Role name must be unique");
        }
        Role role = roleMapper.toEntity(roleDto);
        roleRepo.save(role);
        return roleMapper.toDto(role);
    }

    public void deleteRole(String id) {
        roleRepo.deleteById(id);
    }

    public List<RoleDto> getAllRoles() {

        return roleMapper.toDtos(roleRepo.findAll());
    }


}
