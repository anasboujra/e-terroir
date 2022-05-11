package com.site.eterroir;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.eterroir.model.Admin;
import com.site.eterroir.model.Client;
import com.site.eterroir.model.Cooperative;
import com.site.eterroir.repository.AdminRepo;
import com.site.eterroir.repository.ClientRepo;
import com.site.eterroir.repository.CooperativeRepo;
import com.site.eterroir.service.AdminService;
import com.site.eterroir.service.ClientService;
import com.site.eterroir.service.CooperativeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

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
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

	@Bean
	public CommandLineRunner run(AdminRepo adminRepo) {
		return args -> {
			if(adminRepo.findAll().isEmpty()){
				Admin admin = new Admin("anasboujra@email.com", passwordEncoder().encode("0000"),
						"0604384177", "Boujra", "Anas", "Jk36429", LocalDate.of(1999, 5, 28));
				adminRepo.save(admin);
			}
		};
	}
}
