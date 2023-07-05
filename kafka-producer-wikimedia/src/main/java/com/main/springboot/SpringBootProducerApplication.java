package com.main.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.main.springboot.kafka.producer.WikimediaRecentChangeProducer;

@SpringBootApplication
public class SpringBootProducerApplication implements CommandLineRunner {
	
	@Autowired
	private WikimediaRecentChangeProducer wikimediaRecentChangeProducer;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProducerApplication.class);

	}

	@Override
	public void run(String... args) throws Exception {
		wikimediaRecentChangeProducer.sendMessage();
		
	}

}
