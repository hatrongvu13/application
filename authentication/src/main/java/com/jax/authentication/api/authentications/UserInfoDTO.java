package com.jax.authentication.api.authentications;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDTO {
    private TokenUser detail;
}
