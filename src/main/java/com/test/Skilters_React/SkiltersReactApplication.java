package com.test.Skilters_React;

import java.util.Calendar;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.test.Skilters_React.model.User;
import com.test.Skilters_React.model.User.t_status;
import com.test.Skilters_React.model.UserRepository;

@SpringBootApplication
public class SkiltersReactApplication {
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(SkiltersReactApplication.class, args);
	}
}

