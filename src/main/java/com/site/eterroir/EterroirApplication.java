package com.site.eterroir;

import com.site.eterroir.model.Admin;
import com.site.eterroir.model.Client;
import com.site.eterroir.model.Cooperative;
import com.site.eterroir.service.AdminService;
import com.site.eterroir.service.ClientService;
import com.site.eterroir.service.CooperativeService;
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
	public CommandLineRunner run(AdminService adminService,
								 CooperativeService cooperativeService,
								 ClientService clientService) throws Exception {
		return args -> {
			if(adminService.list().isEmpty()){
				adminService.create(new Admin("admin@email.com", "0000"));
			}
			if(cooperativeService.list().isEmpty()){
				cooperativeService.create(new Cooperative("cooperative@email.com", "0000"));
			}
			if(clientService.list().isEmpty()){
				clientService.create(new Client("client@email.com", "0000"));
			}
		};
	}
}
