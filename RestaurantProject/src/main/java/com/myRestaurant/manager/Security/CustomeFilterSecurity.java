package com.myRestaurant.manager.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//public class CustomeFilterSecurity {
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http.cors().disable()
//			.csrf().disable()
//			.authorizeHttpRequests()
//			.requestMatchers("/login/**")
//			.antMatchers("/homepage/**").authenticated()  // Cần xác thực cho các trang homepage
//			.permitAll();
//		return http.build();
//	}
//}
@Configuration
@EnableWebSecurity
public class CustomeFilterSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable()
            .csrf().disable()
            .authorizeRequests()
            .requestMatchers("/login/**").permitAll()  // Cho phép không cần xác thực đối với các route login
            .requestMatchers("/homepage/**").permitAll();
        return http.build();
    }
}

