package com.modernfrontendshtmx.bookmarks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {
  @Bean
  public PasswordEncoder encoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    UserDetails userDetails = User.builder()
                                  .username("admin")
                                  .password(encoder.encode("admin"))
                                  .build();
    InMemoryUserDetailsManager inMemoryUserDetailsManager =
        new InMemoryUserDetailsManager();
    inMemoryUserDetailsManager.createUser(userDetails);
    return inMemoryUserDetailsManager;
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(
            registry
            -> registry.requestMatchers(HttpMethod.GET, "/css/**")
                   .permitAll()
                   .requestMatchers(HttpMethod.GET, "/webjars/**")
                   .permitAll()
                   .anyRequest()
                   .authenticated())
        .formLogin(Customizer.withDefaults())
        .build();
  }
}
