package com.joy.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author joy
 */
@SpringBootApplication
@MapperScan("com.joy.member.dao")
public class Mall3MemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mall3MemberApplication.class, args);
	}

}
