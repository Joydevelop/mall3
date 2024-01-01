package com.joy.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author joy
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.joy.ware.dao")
@EnableDiscoveryClient
public class Mall3WareApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mall3WareApplication.class, args);
	}

}
