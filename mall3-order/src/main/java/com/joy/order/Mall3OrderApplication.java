package com.joy.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author joy
 */
@SpringBootApplication
@MapperScan("com.joy.order.dao")
@EnableDiscoveryClient
public class Mall3OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mall3OrderApplication.class, args);
	}

}
