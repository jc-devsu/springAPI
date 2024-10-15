package com.devsu.cliente_persona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.devsu.cliente_persona.model")
public class ClientePersonaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientePersonaApplication.class, args);
	}

}
