package com.users.users.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    private  RoleService roleService;



    @PostMapping(value = "/add")
    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto) {
        RoleDto addedRole = roleService.addRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRole);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roleDtos = roleService.getAllRoles();
        return ResponseEntity.ok(roleDtos);
    }
}