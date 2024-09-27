package com.ensah;

import com.ensah.core.bo.User;
import com.ensah.core.config.RsaKeyProperties;
import com.ensah.core.dao.IUserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ExamPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamPlannerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(IUserDao users, PasswordEncoder encoder) {
		return args -> {
			users.save(new User("user",encoder.encode("password"),"ROLE_USER"));
			users.save(new User("admin",encoder.encode("password"),"ROLE_USER,ROLE_ADMIN"));
		};
	}

}
