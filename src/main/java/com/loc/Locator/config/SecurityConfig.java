package com.loc.Locator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf-> csrf.disable());
//		authorizeHttpRequests(req-> req.requestMatchers("/location/**").permitAll())//
//				.requestMatchers(HttpMethod.GET , "/location/verify").hasRole("MANAGER")//
//				.requestMatchers("/location/add").hasRole("EMPLOYEE"))//
	//	.httpBasic(Customizer.withDefaults());
		
		
		return httpSecurity.build();
	}
}
