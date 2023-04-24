package com.renanpelicari.minesweeper;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@OpenAPIDefinition(info = @Info(
		title = "Minesweeper API",
		version = "1.0",
		description = "The API for Minesweeper Game",
		contact = @Contact(name = "Renan Peliçari Rodrigues",
				email = "renanpelicari@gmail.com",
				url = "https://www.linkedin.com/in/renanpelicari/")
))
public class MinesweeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinesweeperApplication.class, args);
	}

}
