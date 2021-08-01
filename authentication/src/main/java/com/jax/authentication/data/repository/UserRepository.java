package com.jax.authentication.data.repository;

import com.jax.authentication.data.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Boolean existsUserByEmail(String email);

    Boolean existsUserByUsername(String username);
}
