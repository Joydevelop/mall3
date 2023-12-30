package com.joy.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author joy
 */
@SpringBootApplication
@MapperScan("com.joy.order.dao")
public class Mall3OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mall3OrderApplication.class, args);
	}

}
