package com.spave.backend.spave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaveApplication.class, args);
	}

}
