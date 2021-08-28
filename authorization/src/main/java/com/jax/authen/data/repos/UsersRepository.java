package com.jax.authen.data.repos;

import com.jax.authen.data.entities.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<Users, ObjectId> {
    Optional<Users> findUsersByUsername(String username);
}
