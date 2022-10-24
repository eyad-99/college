package com.example.college.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	AuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/student/**").hasRole("STUDENT")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/instructor/**").hasRole("INSTRUCTOR")
		.antMatchers("/protected").authenticated()
		.and()
		.formLogin()
		.successHandler(successHandler)
		.permitAll()
		.and()
		.httpBasic();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		return NoOpPasswordEncoder.getInstance();
	}
}