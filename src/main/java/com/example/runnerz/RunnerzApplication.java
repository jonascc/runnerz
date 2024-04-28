package com.example.runnerz;

import com.example.runnerz.run.Location;
import com.example.runnerz.run.Run;
import com.example.runnerz.run.RunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class RunnerzApplication {

	private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(RunnerzApplication.class, args);
		log.info("Application started successfully");
	}

	@Bean // it's run after the context is loaded
	CommandLineRunner runner(RunRepository repository) {
		return args -> {
			Run run = new Run(1, "First run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR);
			log.info("Run: " + run);
			repository.create(run);
		};
	}

}
