package com.example.mybookshelfapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Setter
@Getter
@Component
@Primary
@ConfigurationProperties(prefix = "rsa")
public class RsaKeyProperties {

    RSAPublicKey publicKey;
    RSAPrivateKey privateKey;
}