package org.factoriaf5.happypaws.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.factoriaf5.happypaws.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /* private CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    } */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                        .requestMatchers("/api/auth/login").hasRole("USER") // endpoints de login
                        .anyRequest().permitAll() // el resto requiere autenticación
                )
                // .userDetailsService(customUserDetailsService)
                .httpBasic(withDefaults()); // Basic Auth actualizado para 6.1
        return http.build();
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    //     return authConfig.getAuthenticationManager();
    // }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails user = User.builder().username("pepito").password("1234").roles("USER").build();

        Collection<UserDetails> users = new ArrayList<>();

        users.add(user);

        return new InMemoryUserDetailsManager(users);

    }
}
