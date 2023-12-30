package com.joy.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author joy
 */
@SpringBootApplication
@MapperScan("com.joy.coupon.dao")
public class Mall3CouponApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mall3CouponApplication.class, args);
	}

}
