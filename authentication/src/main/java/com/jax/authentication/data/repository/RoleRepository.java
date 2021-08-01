package com.jax.authentication.data.repository;

import com.jax.authentication.data.entities.Roles;
import com.jax.authentication.data.enums.ERole;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Roles, ObjectId> {

    Optional<Roles> findRolesByName(ERole name);

}
