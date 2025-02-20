package es.grupo18.jobmatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "es.grupo18.jobmatcher")
public class JobmatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobmatcherApplication.class, args);
	}

}
