// package org.factoriaf5.happypaws.config;

// import static org.springframework.security.config.Customizer.withDefaults;

// import java.util.ArrayList;
// import java.util.Collection;

// import org.factoriaf5.happypaws.security.JpaUserDetailsService;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration {

//         @Value("${api-endpoint}")
//         String endpoint;

//         private JpaUserDetailsService jpaUserDetailsService;

//         public SecurityConfiguration(JpaUserDetailsService jpaUserDetailsService) {
//                 this.jpaUserDetailsService = jpaUserDetailsService;
//         }

//         @Bean
//         SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//                 http
//                         .cors(withDefaults())
//                         .csrf(csrf -> csrf
//                                         .ignoringRequestMatchers("/h2-console/**")
//                                         .disable())
//                         .headers(header -> header
//                                         .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
//                         // Solo se permite que tu aplicación sea embebida en un <iframe> si el origen (dominio) es el mismo.
//                         .formLogin(form -> form.disable())
//                         .logout(out -> out
//                                 .logoutUrl(endpoint + "/logout")
//                                 .invalidateHttpSession(true)
//                                 .deleteCookies("JSESSIONID"))
//                         .authorizeHttpRequests(auth -> auth
//                                         .requestMatchers("h2-console/**").permitAll()
//                                         .requestMatchers("/public").permitAll()
//                                         .requestMatchers(HttpMethod.POST, endpoint +"/register").permitAll()
//                                         .requestMatchers(endpoint + "/login").hasAnyRole("USER", "ADMIN") // principio de mínimos privilegios
//                                         .requestMatchers(HttpMethod.GET, endpoint + "/private").hasRole("ADMIN")
//                                         .anyRequest().authenticated())
//                         .userDetailsService(jpaUserDetailsService)
//                         .httpBasic(withDefaults())
//                         .sessionManagement(session -> session
//                                         .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

//                 // http.headers(header -> header.frameOptions(frame -> frame.sameOrigin()));

//                 return http.build();
//         }

//         /* 
//          * https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html
//          */

//         /* @Bean
//         public InMemoryUserDetailsManager userDetailsManager() {

//                 UserDetails mickey = User.builder()
//                                 .username("mickey")
//                                 .password("{noop}mouse")
//                                 .roles("ADMIN")
//                                 .build();

//                 UserDetails minnie = User.builder()
//                                 .username("minnie")
//                                 .password("{noop}mouse")
//                                 .roles("USER")
//                                 .build();

//                 Collection<UserDetails> users = new ArrayList<>();
//                 users.add(mickey);
//                 users.add(minnie);

//                 return new InMemoryUserDetailsManager(users);
//         } */

// }
