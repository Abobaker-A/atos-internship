package com.users.users.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends MongoRepository<User, String> {
    Page<User> findAll(Pageable pageable);

    Optional<User> findByEmail(String studentEmail);

    Optional<User> findByMobileNumber(String mobileNumber);

}
