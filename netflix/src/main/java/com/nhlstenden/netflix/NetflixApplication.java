package com.nhlstenden.netflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // This annotation includes @ComponentScan, @Configuration, and @EnableAutoConfiguration
public class NetflixApplication {
	public static void main(String[] args) {
		SpringApplication.run(NetflixApplication.class, args);
	}
}