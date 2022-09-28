package com.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {
	
	protected void configure(HttpSecurity http) throws Exception{
			
		//with csrf that isdisabled incase you are using html and not thymeleaf
		
		
		http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/h2-console/**").and()
		.authorizeRequests()
		.mvcMatchers("/dashboard").authenticated()
		.antMatchers("/home").permitAll()
		.antMatchers(HttpMethod.POST,"/holidays/**").permitAll()
		.mvcMatchers(HttpMethod.POST,"/contact").permitAll()
		.mvcMatchers("/saveMsg").permitAll()
		.mvcMatchers("/courses").permitAll()
		//.mvcMatchers("/courses").authenticated()
		.mvcMatchers("/about").permitAll()
		.mvcMatchers("/login").permitAll()
		.and().formLogin().loginPage("/login")
		.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
		.and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
		.and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
		.and().httpBasic();
		
		//Our h2-console is using a frameoption to display content which will bring error
		//bcos of the spring security. We will need to disable it
		
		http.headers().frameOptions().disable();
		
		 
		
		//Without csrf
		//http.authorizeRequests()
		//	.mvcMatchers("/home").permitAll()
			//.mvcMatchers("/holidays/**").permitAll()
		//	.mvcMatchers("/contact").permitAll()
		//	.mvcMatchers("/saveMsg").permitAll()
		//	.mvcMatchers("/courses").permitAll()
			//.mvcMatchers("/courses").authenticated()
		//	.mvcMatchers("/about").permitAll()
			//.and().formLogin().and().httpBasic();
		}
	
	@Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.inMemoryAuthentication()
		 	.withUser("user").password("12345").roles("USER")
		 	.and()
		 	.withUser("Esther").password("54321").roles("USER","ESTHER")
		 	.and().passwordEncoder(NoOpPasswordEncoder.getInstance());
	 }
		
	
	
	
	//denyAll  method
	//@Override
	//protected void configure(HttpSecurity http) throws Exception{
	//	http.authorizeRequests().anyRequest().denyAll()
	//	.and().formLogin()
		//	.and().httpBasic();
	//}
	
	
	
	
	
	//permitAll() method
	//@Override
	//protected void configure(HttpSecurity http) throws Exception{
	//	http.authorizeRequests().anyRequest().permitAll()
	//	.and().formLogin()
	//		.and().httpBasic();
	//}
	
}
      