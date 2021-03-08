package com.nse.user.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	// This is to eliminate the FORBIDDEN : 403 error
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	    .csrf().disable()
	    .authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
	  }
	
}
