package com.jax.authen.data.repos;

import com.jax.authen.data.entities.Roles;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RolesRepository extends MongoRepository<Roles, ObjectId> {
}
