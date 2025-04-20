package com.dsa.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable()) // Disable CSRF for simplicity here
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/register").permitAll() // Allow access to registration without authentication
						.requestMatchers("/login").anonymous() // Allow only anonymous users to access /login
						.requestMatchers("/logout").authenticated() // Allow authenticated users to /logout
						.anyRequest().authenticated()) // All other requests need authentication
				.formLogin((form) -> form
						.permitAll()
						.defaultSuccessUrl("/api/profile", true)) // Redirect to /api/profile after successful login
//				.logout((logout) -> logout.permitAll())
//              .httpBasic((basic) -> basic.disable()) // Disable Spring's default HTTP Basic authentication
				.exceptionHandling((exceptions) -> exceptions
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

		log.info("Configured SecurityFilterChain: {}", http);

		return http.build();
	}

}
