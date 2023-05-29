package com.users.users.roles;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepo extends MongoRepository<Role,String> {
    List<Role> findByNameIn(List<String> names);

    Optional<Object> findByName(String name);
}
