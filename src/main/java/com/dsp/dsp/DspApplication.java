package com.dsp.dsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DspApplication {

	public static void main(String[] args) {
		SpringApplication.run(DspApplication.class, args);
		
	
	}

}
