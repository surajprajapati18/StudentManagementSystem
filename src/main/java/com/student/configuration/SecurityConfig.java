package com.student.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails normal_user=User.withUsername("normal").password(passwordEncoder().encode("normal")).roles("normal").build();
		UserDetails admin_user=User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("admin").build();
		return new InMemoryUserDetailsManager(normal_user,admin_user);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception
	{
		security.csrf().disable()
		.authorizeHttpRequests()
//		.requestMatchers("/admin").hasRole("admin")
//		.requestMatchers("/normal").hasRole("normal")
		
		.requestMatchers("/public")
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin();
		return security.build();
		
	}

}
