package com.ahmad.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*@Configuration
@EnableWebSecurity*/
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/user/**").hasRole("STUDENT").antMatchers("/user/**")
				.hasRole("ALUMNI").antMatchers("/user/**").hasRole("EMPLOYEE").antMatchers("/admin/**")
				.hasRole("ADMIN");

	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Autowired public void configAuthentication(AuthenticationManagerBuilder
			 auth) throws Exception {
			 
			 auth.jdbcAuthentication().dataSource(datasource)
			  .authoritiesByUsernameQuery("select email,authority from UserAuthorities where email=?"
			  )
			 .usersByUsernameQuery("select email,password,enabled from user_check where email=?"
			  );
	}
}

	/*
	 * private static String REALM = "REALM_NAME";
	 * 
	 * @Autowired DataSource datasource;
	 * 
	 * @Autowired public void
	 * configureGlobalSecurity(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * auth.inMemoryAuthentication().withUser("aqureshi536@gmail.com").password(
	 * "050325").roles("STUDENT");
	 * 
	 * }
	 */

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * System.out.println("In security");
	 * http.authorizeRequests().antMatchers("/admin/**").access(
	 * "hasRole('ROLE_ADMIN')").antMatchers("/user/**")
	 * .access("hasRole('ROLE_STUDENT')").antMatchers("/user/**").access(
	 * "hasRole('ROLE_EMPLOYEE')")
	 * .antMatchers("/user/**").access("hasRole('ROLE_ALUMNI')").and().formLogin
	 * ().loginPage("/login").defaultSuccessUrl("/user/blogs/")
	 * .loginProcessingUrl("/j_spring_security_check").usernameParameter("email"
	 * ).passwordParameter("password")
	 * .and().logout().logoutSuccessUrl("/login?logout").logoutUrl(
	 * "/j_spring_security_logout")
	 * .invalidateHttpSession(true).and().csrf().disable(); }
	 */

	/*
	 * @Autowired public void configAuthentication(AuthenticationManagerBuilder
	 * auth) throws Exception {
	 * 
	 * auth.jdbcAuthentication().dataSource(datasource)
	 * .authoritiesByUsernameQuery("select email,authority from UserAuthorities where email=?"
	 * )
	 * .usersByUsernameQuery("select email,password,enabled from user_check where email=?"
	 * );
	 * 
	 * }
	 */
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.csrf().disable().authorizeRequests().antMatchers("/user/**")
	 * .hasRole("STUDENT").antMatchers("/user/**").hasRole("ALUMNI").antMatchers
	 * ("/user/**")
	 * .hasRole("EMPLOYEE").antMatchers("/admin/**").hasRole("ADMIN").and().
	 * httpBasic().realmName(REALM)
	 * .authenticationEntryPoint(getBasicAuthorityPoint()).and().
	 * sessionManagement()
	 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 * 
	 * }
	 * 
	 * @Bean public CustomBasicAuthenticationEntryPoint getBasicAuthorityPoint()
	 * { return new CustomBasicAuthenticationEntryPoint(); }
	 * 
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**"); }
	 */

