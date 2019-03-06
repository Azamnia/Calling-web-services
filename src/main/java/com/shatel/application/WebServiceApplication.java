package com.shatel.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shatel.file.Numbers;
import com.shatel.send.Sending;



@SpringBootApplication
public class WebServiceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebServiceApplication.class, args);
		
		Numbers.main(args);
		Sending.msisdn();

	}
}


