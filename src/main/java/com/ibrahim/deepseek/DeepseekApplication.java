package com.ibrahim.deepseek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeepseekApplication {

	private static final Logger log =
			LoggerFactory.getLogger(DeepseekApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DeepseekApplication.class, args);
		log.info("DeepSeek STARTED");
	}

}
