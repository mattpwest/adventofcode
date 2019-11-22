package com.claudeheyman.adventofcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.claudeheyman" })
@SpringBootApplication
public class AdventOfCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdventOfCodeApplication.class, args);
	}

}
