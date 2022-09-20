package com.cg.snakeList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.*;


@SpringBootApplication
@EnableCaching
/*
 *  @Configuration -replaces @Configuration and annotates class as a configuration
 *  @EnableAutoConfiguration - tells SP to configure Beans
 *  @ComponentScan -  tells to SP to scan current package and  subpackage
*/
public class SnakeListApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnakeListApplication.class, args);
	}

}
