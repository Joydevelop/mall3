package com.joy.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author joy
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.joy.product.dao")
public class Mall3ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mall3ProductApplication.class, args);
	}

}
