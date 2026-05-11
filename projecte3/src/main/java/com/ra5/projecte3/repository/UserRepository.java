package com.ra5.projecte3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ra5.projecte3.model.Role;
import com.ra5.projecte3.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByRole(Role role);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
