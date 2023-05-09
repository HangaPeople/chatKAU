package com.chatkau.security.config;

import com.chatkau.security.filter.JsonLoginProcessingFilter;
import com.chatkau.security.filter.JwtAuthenticationFilter;
import com.chatkau.security.handler.CustomAccessDeniedHandler;
import com.chatkau.security.handler.JsonAuthenticationFailureHandler;
import com.chatkau.security.handler.JsonAuthenticationSuccessHandler;
import com.chatkau.security.handler.JsonLoginEntryPoint;
import com.chatkau.security.provider.JsonAuthenticationProvider;
import com.chatkau.security.provider.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler jsonAuthenticationFailureHandler() {
        return new JsonAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler jsonAuthenticationSuccessHandler() {
        return new JsonAuthenticationSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationEntryPoint jsonLoginEntryPoint() {
        return new JsonLoginEntryPoint();
    }

    @Bean
    public AuthenticationProvider jsonAuthenticationProvider() {
        JsonAuthenticationProvider jsonAuthenticationProvider = new JsonAuthenticationProvider();
        jsonAuthenticationProvider.setUserDetailsService(userDetailsService);
        jsonAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return jsonAuthenticationProvider;
    }

    @Bean
    public AuthenticationProvider jwtAuthenticationProvider() {
        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider();
        jwtAuthenticationProvider.setUserDetailsService(userDetailsService);

        return jwtAuthenticationProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
        ProviderManager p = (ProviderManager) authenticationManager;
        p.getProviders().add(jsonAuthenticationProvider());
        p.getProviders().add(jwtAuthenticationProvider());

        return http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/test").authenticated()
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/**").permitAll())

                .addFilterAt(jsonLoginProcessFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(authenticationManager), JsonLoginProcessingFilter.class)

                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler())
                .authenticationEntryPoint(jsonLoginEntryPoint())
                .and()
                .build();
    }

    public JsonLoginProcessingFilter jsonLoginProcessFilter(AuthenticationManager authenticationManager) {
        JsonLoginProcessingFilter jsonLoginProcessingFilter = new JsonLoginProcessingFilter();
        jsonLoginProcessingFilter.setAuthenticationManager(authenticationManager);

        jsonLoginProcessingFilter.setAuthenticationFailureHandler(jsonAuthenticationFailureHandler());
        jsonLoginProcessingFilter.setAuthenticationSuccessHandler(jsonAuthenticationSuccessHandler());
        return jsonLoginProcessingFilter;
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new JwtAuthenticationFilter(authenticationManager);
    }
}
