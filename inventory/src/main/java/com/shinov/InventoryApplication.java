package com.shinov;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import DAL.ItemRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication

public class InventoryApplication {

	private static final Logger log = LoggerFactory.getLogger(InventoryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
		
	}

}