package ru.lighthouse.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.lighthouse.mobile.Uri.TEST_SERVICE_URI;

@SpringBootApplication
@RestController
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@GetMapping(TEST_SERVICE_URI)
	public String helloGradle() {
		return "Hello mobile!";
	}

}