package com.jax.authentication.data.entities;

import com.jax.authentication.data.enums.ERole;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "roles")
public class Roles {

    @Id
    private ObjectId id;

    private ERole name;
}
