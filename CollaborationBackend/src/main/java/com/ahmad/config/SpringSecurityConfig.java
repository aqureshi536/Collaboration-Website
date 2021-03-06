package com.ahmad.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("In http confg");
		http.csrf().disable().authorizeRequests().antMatchers("/user/**")
				.access("hasAnyRole('ROLE_STUDENT,ROLE_ALUMNI,ROLE_EMPLOYEE,ROLE_ADMIN')").and().httpBasic();
	}

	/*
	 * @Bean public AuthenticationManager authenticationManager() throws
	 * Exception { return super.authenticationManager(); }
	 */
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("In jdbc auth confg");
		auth.jdbcAuthentication().dataSource(datasource)
				.authoritiesByUsernameQuery("select email,authority from UserAuthorities where email=?")
				.usersByUsernameQuery("select email,password,enabled from user_check where email=?");
	}
}

/*
 * @Bean public CustomBasicAuthenticationEntryPoint getBasicAuthorityPoint() {
 * return new CustomBasicAuthenticationEntryPoint(); }
 * 
 * @Override public void configure(WebSecurity web) throws Exception {
 * web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**"); }
 */
