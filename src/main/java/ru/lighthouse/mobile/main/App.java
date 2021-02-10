package ru.lighthouse.mobile.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {
	public final static String HEALTH_URI = "/health-check";
	public final static String HEALTH_RESPONSE = "I AM MOBILE! I AM FINE!";

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@GetMapping(HEALTH_URI)
	public String helloGradle() {
		return HEALTH_RESPONSE;
	}
}