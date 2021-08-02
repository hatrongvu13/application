package com.jax.authentication.api.authentications;

import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Data
@Component
public class TokenKeyProvider {

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    @Autowired
    @SneakyThrows
    public TokenKeyProvider(@Value("${authentication.token.jwt.publickey}") String publickey,
                            @Value("${authentication.token.jwt.privatekey}")String privatekey) {

        this.privateKey = RSAUtils.getPrivateKeyFromString(privatekey);
        this.publicKey = RSAUtils.getPublicKeyFromString(publickey);
    }
}
