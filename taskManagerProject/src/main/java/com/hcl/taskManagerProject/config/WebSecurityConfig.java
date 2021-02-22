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
        .antMatchers("/viewTasks").hasRole("USER")
        .antMatchers("/updateTask").hasRole("USER")
        .antMatchers("/createTask").hasRole("USER")
        .antMatchers("/deleteTask").hasRole("USER")
        .antMatchers("/deleteUser").hasRole("ADMIN")
        .antMatchers("/adminWelcome").hasRole("ADMIN")
        .antMatchers("/deleteAnotherUser").hasRole("ADMIN")
        .antMatchers("/welcome").permitAll()
        .and().csrf().disable()
        .formLogin()
        .loginPage("/login").failureUrl("/login?error=true")
        .defaultSuccessUrl("/welcome")
        .usernameParameter("userName")
        .passwordParameter("password")
        .and().logout(logout -> logout  
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/")                        
        .invalidateHttpSession(true)                                        
        ).exceptionHandling()
        .accessDeniedPage("/403");
	}

}
