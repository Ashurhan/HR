package com.example.HR.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {}) // Включение CORS
                .csrf(csrf -> csrf.disable()) // Отключение CSRF
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/swagger-resources/**",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/job_seeker/**",
                                "/category/**",
                                "/admin/**",
                                "/employer/**",
                                "/vacancy/**",
                                "/api/v1/management/**",
                                "/chatting/**",
                                "/file/**",
                                "/api/v1/auth/**",
                                "/ws/**",
                                "/wss/**",
                                "/main/**",
                                "/chat/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/configuration/**",
                                "/public"
                        ).permitAll() // Разрешаем доступ к указанным ресурсам
                        .anyRequest().authenticated() // Все остальные запросы требуют авторизации
                )
                .formLogin(form -> form
                        .failureHandler(customAuthenticationFailureHandler()) // Обработчик ошибок
                );

        // Добавление фильтра для обработки JWT
        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider),
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean(name = "securityPasswordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}