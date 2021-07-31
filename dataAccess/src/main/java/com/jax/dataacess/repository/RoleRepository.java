package com.jax.dataacess.repository;

import com.jax.models.entities.Roles;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Roles, ObjectId> {
}
