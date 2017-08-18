package com.todoapp.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.todoapp.httprest.AuthFailureHandler;
import com.todoapp.httprest.AuthSuccessHandler;
import com.todoapp.httprest.HttpAuthenticationEntryPoint;
import com.todoapp.httprest.HttpLogoutSuccessHandler;
import com.todoapp.service.ToDoUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan("com.todoapp")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	@Autowired
	private HttpAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private AuthSuccessHandler authSuccessHandler;
	@Autowired
	private AuthFailureHandler authFailureHandler;
	@Autowired
	private HttpLogoutSuccessHandler logoutSuccessHandler;
	@Autowired
	private ToDoUserDetailService userDetailsService;

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests()
	 * .antMatchers("/admin/**").access("hasRole('ROLE_USER')") .and()
	 * .formLogin().loginPage("/login").failureUrl("/login?error")
	 * .usernameParameter("username").passwordParameter("password") .and()
	 * .logout().logoutSuccessUrl("/login?logout") .and() .csrf(); }
	 */

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password, enabled from user where username=?")
				.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());

		return authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authenticationProvider(authenticationProvider()).exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint).and().formLogin().permitAll()
				.loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
				.successHandler(authSuccessHandler).failureHandler(authFailureHandler).and().logout().permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/login?logout", "DELETE"))
				.logoutSuccessHandler(logoutSuccessHandler).and().sessionManagement().maximumSessions(1);
		http.authorizeRequests().antMatchers("/**").permitAll();
		http.authorizeRequests().antMatchers("/createUser*").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.cors().disable();
	}

}
