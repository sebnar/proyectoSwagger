package com.macroproyectos.cargue.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Enables in-memory authentication, CORS policy etc. Used by Swagger.
 * 
 * @author alcides.sanchez
 */
@Profile("dev")
@Configuration
public class DevWebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// this is just for the H2 DB and testing purposes
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll().and().authorizeRequests()
				.antMatchers("/v2/api-docs").permitAll().and().authorizeRequests().antMatchers("/swagger*/**")
				.permitAll();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
