package com.alexshopcart;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class AlexShopCart {

	/**
	 * @param args main args
	 */
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-6:00"));
		SpringApplication.run(AlexShopCart.class, args);
	}

}
