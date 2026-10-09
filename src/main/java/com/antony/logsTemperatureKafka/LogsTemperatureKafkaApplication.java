package com.antony.logsTemperatureKafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogsTemperatureKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogsTemperatureKafkaApplication.class, args);
	}

}
