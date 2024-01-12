package com.mdd.auto_test_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mdd.auto_test_back.mapper")
public class AutoTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoTestApplication.class, args);
	}

}
