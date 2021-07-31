package com.jax.dataacess.repository;

import com.jax.models.entities.Account;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, ObjectId> {
}
