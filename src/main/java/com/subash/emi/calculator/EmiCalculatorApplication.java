package com.subash.emi.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication
public class EmiCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmiCalculatorApplication.class, args);
	}

}
