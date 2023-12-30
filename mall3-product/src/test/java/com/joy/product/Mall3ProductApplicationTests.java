package com.joy.product;

import com.joy.product.entity.BrandEntity;
import com.joy.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
class Mall3ProductApplicationTests {
	@Autowired
	private BrandService brandService;

	@Test
	void contextLoads() {
		List<BrandEntity> list = brandService.list();
		System.out.println(list);
	}

}
