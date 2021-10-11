package com.shinov;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import DAL.ItemRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class InventoryApplication {

	private static final Logger log = LoggerFactory.getLogger(InventoryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ItemRepository repository) {
		return (args) -> {

			
			
			
		};
	}


}