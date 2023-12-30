package com.joy.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author joy
 */
@SpringBootApplication
@MapperScan("com.joy.product.dao")
public class Mall3ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mall3ProductApplication.class, args);
	}

}
