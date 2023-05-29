package com.users.users.users;

import com.users.users.mapper.RoleMapper;
import com.users.users.mapper.UserMapper;
import com.users.users.roles.Role;
import com.users.users.roles.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserMapper userMapper ;
    @Autowired
    private RoleMapper roleMapper ;
    @Autowired
    private MongoTemplate mongoTemplate;

    

    public List<UserDto> getAllUsers() {
        List<User> users = usersRepo.findAll();
        return userMapper.fromEntityToDto(users);
    }

    public UserDto getUserById(String id) {
        Optional<User> user = usersRepo.findById(id);
        if(user.isPresent()) {
            return userMapper.toUserDto(user.get());
        }
        return null;
    }
    public UserDto getUserByEmail(String email) {
        Optional<User> user = usersRepo.findByEmail(email);
        if (user.isPresent()) {
            return userMapper.toUserDto(user.get());
        }
        return null;
    }


    public Page<UserDto> getAllUsersPages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = usersRepo.findAll(pageable);

        return new PageImpl<>(userPage.getContent().stream().map(userMapper::toUserDto).collect(Collectors.toList()), pageable, userPage.getTotalElements());
    }
    public UserDto assignRoles(String userId, List<String> roleIds) {
        User user = usersRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Role> roles = roleRepo.findAllById(roleIds);
        user.setRoles(roles);
        usersRepo.save(user);
        return userMapper.toUserDto(user);
    }



}
