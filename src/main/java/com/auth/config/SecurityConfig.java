package com.auth.config;

import org.springframework.beans.factory.annotation.Autowired;

//import java.beans.Customizer;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtFilter jwtFilter;
@Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	     http.csrf(customizer->customizer.disable());
	     http.cors(Customizer.withDefaults()) ;
		 http.authorizeHttpRequests(request->request.
				 requestMatchers("/users/**","/orders/**","/pdf/**","/searchproduct","/allprod","/deleteprod/**","/updateprod/**","address/**")
				 .permitAll().anyRequest().authenticated());
//         http.formLogin(Customizer.withDefaults());
         http.httpBasic();
         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		 http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
         return http.build();
			
}
//@Bean
//public UserDetailsService userDetailsService() {
//	UserDetails user=User
//			.withDefaultPasswordEncoder()
//			.username("paras")
//			.password("paras123")
//			.roles("USER")
//			.build();
//	UserDetails user1=User
//			.withDefaultPasswordEncoder()
//			.username("sahil")
//			.password("sahil123")
//			.roles("ADMIN ")
//			.build();
//	return new InMemoryUserDetailsManager(user,user1) ;
//	
//}
 @Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(12);
}
 @Bean
 public AuthenticationProvider authenticationProvider() {
	 DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
	 provider.setUserDetailsService(userDetailsService);
//	 provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
	 provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
	 return provider;
 }
 @Bean
 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	 return config.getAuthenticationManager();
 }
 @Bean
 public CorsConfigurationSource corsConfigurationSource() {
     CorsConfiguration config = new CorsConfiguration();
     config.addAllowedOrigin("http://localhost:4200");
     config.addAllowedMethod("*");
     config.addAllowedHeader("*");
     config.setAllowCredentials(true);

     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     source.registerCorsConfiguration("/**", config);
     return source;
 }
 @Bean
 public WebMvcConfigurer configurer() {
     return new WebMvcConfigurer() {
         @Override
         public void addResourceHandlers(ResourceHandlerRegistry registry) {
             registry.addResourceHandler("/uploads/**")
                 .addResourceLocations("file:uploads/");
         }
     };
 }

}
