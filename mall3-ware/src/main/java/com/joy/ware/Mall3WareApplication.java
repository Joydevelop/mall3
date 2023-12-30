package com.joy.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author joy
 */
@SpringBootApplication
@MapperScan("com.joy.ware.dao")
@EnableDiscoveryClient
public class Mall3WareApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mall3WareApplication.class, args);
	}

}
