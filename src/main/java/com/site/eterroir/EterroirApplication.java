package com.site.eterroir;

import com.site.eterroir.model.Admin;
import com.site.eterroir.service.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EterroirApplication {

	public static void main(String[] args) {
		SpringApplication.run(EterroirApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner run(AdminService adminService) throws Exception {
		return args -> {
			if(adminService.list().isEmpty()){
				adminService.create(new Admin("admin@email.com", "0000"));
			}
		};
	}
}
