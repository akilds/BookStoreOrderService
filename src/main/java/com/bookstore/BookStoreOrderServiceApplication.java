package com.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookStoreOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreOrderServiceApplication.class, args);
	}

}
