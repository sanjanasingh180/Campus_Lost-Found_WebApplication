package com.lostandfound.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.lostandfound.services.*;



@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter 
{

	@Autowired
	private UserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		System.out.println("Hello");
		 	http.csrf().disable();
		
	        http.authorizeRequests()        
	        .antMatchers("/").permitAll()
	        .antMatchers("/web/**").permitAll()
	                
	        
	        .antMatchers("/user/**").hasRole("USER")
	        .and()        
	        .formLogin().loginPage("/web/login").permitAll()
	        .defaultSuccessUrl("/web/home").usernameParameter("email")
	        .and()
	        .exceptionHandling().accessDeniedPage("/web/accessDenied").and()
	        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).logoutSuccessUrl("/web/login")
	        .and().httpBasic();		
	}
	 @Bean
	    public PasswordEncoder getPasswordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	    }
}
