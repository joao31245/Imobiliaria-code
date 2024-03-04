package com.jotace.imob;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Imobiliaria Application", version = "1", description = "Api that serves an imobiliaria"))

public class ImobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImobApplication.class, args);
	}

}
