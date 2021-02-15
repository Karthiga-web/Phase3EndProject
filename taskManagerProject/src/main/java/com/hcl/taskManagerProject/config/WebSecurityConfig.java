package com.hcl.taskManagerProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hcl.taskManagerProject.service.MyUserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	MyUserDetailsService userDetailsService;
	
	
	@Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
     return new BCryptPasswordEncoder();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordencoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/decide").permitAll()
        .antMatchers("/register").permitAll()
//        .antMatchers("/viewTasks").access("hasRole('USER')")
//        .antMatchers("/updateTask").access("hasRole('USER')")
//        .antMatchers("/createTask").access("hasRole('USER')")
//        .antMatchers("/deleteTask").access("hasRole('USER')")
//        .antMatchers("/welcome").access("hasRole('USER')")
        .antMatchers("/welcome").permitAll()
        .antMatchers("/createTask").permitAll()
        .antMatchers("/viewTasks").permitAll()
        .antMatchers("/updateTask").permitAll()
        .antMatchers("/createTask").permitAll()
        .antMatchers("/deleteTask").permitAll()
       
        .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
        .authenticated().and().csrf().disable().formLogin()
        .loginPage("/login").failureUrl("/login?error=true")
        .defaultSuccessUrl("/welcome")
        .usernameParameter("userName")
        .passwordParameter("password")
        .and().logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login").and().exceptionHandling()
        .accessDeniedPage("/access-denied");
	}

}