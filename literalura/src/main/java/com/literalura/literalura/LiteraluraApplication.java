package com.literalura.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LiteraluraApplication.class, args);
		Principal principal = context.getBean(Principal.class);
		principal.mostrarMenu();
	}

	@Override
	public void run(String... args) {
	}
}
