package com.example.mybookshelfapi;

import com.example.mybookshelfapi.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class MyBookshelfApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBookshelfApiApplication.class, args);
	}

}
