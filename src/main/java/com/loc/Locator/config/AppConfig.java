package com.loc.Locator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class AppConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

		UserDetails admin = User.builder()//
				.username("admin-ad")//
				.password("{noop}Test123")//
				.roles("EMPLOYEE", "MANAGER","ADMIN")//
				.build();

		UserDetails employee1 = User.builder()//
				.username("employee1-ad")//
				.password("{noop}Test123")//
				.roles("EMPLOYEE")//
				.build();

		UserDetails employee2 = User.builder()//
				.username("employee2-ad")//
				.password("{noop}Test123")//
				.roles("EMPLOYEE", "MANAGER","ADMIN")//
				.build();

		return new InMemoryUserDetailsManager(admin, employee1, employee2);
	}
}
