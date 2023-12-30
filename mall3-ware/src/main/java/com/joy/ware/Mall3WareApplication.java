package com.joy.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author joy
 */
@SpringBootApplication
@MapperScan("com.joy.ware.dao")
public class Mall3WareApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mall3WareApplication.class, args);
	}

}
