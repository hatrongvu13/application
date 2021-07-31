package com.jax.models.entities;

import com.jax.models.enums.ERole;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Document(collection = "account")
public class Account {

    @Id
    private ObjectId id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    @DBRef
    private Set<ERole> roles;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private  LocalDateTime updatedDate;
}
