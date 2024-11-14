package com.mygym.security;

import com.mygym.security.jwt.AuthEntryPointJwt;
import com.mygym.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
    /*@Autowired
    UserDetailsServiceImpl userDetailsService;*/

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;


    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                ).csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.
                                requestMatchers("/",
                                        "/favicon.ico",
                                        "/*.png",
                                        "/*.gif",
                                        "/*.svg",
                                        "/*.jpg",
                                        "/*.html",
                                        "/assets/**",
                                        "/*.webmanifest",
                                        "/*.css",
                                        "/*.js").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/users/**").permitAll()
                                .requestMatchers("/api/rutines/**").permitAll()
                                .requestMatchers("/api/product/**").permitAll()
                                .requestMatchers("/api/incidentForm/**").permitAll()
                                .requestMatchers("/api/complaintForm/**").permitAll()
                                .requestMatchers("/api/public/**").permitAll()
                                .requestMatchers("/api/shared/**").authenticated()
                                .requestMatchers("/api/portal/**").authenticated()
                                .requestMatchers("/api/portalDigitalitzacio/**").authenticated()
                                .requestMatchers("/api/digitalitzar/**").hasAnyRole("RHG_DIGITALITZAR")
                                .requestMatchers("/api/portalAdmin/**").hasAnyRole("RHG_RRHH", "RHG_ADMIN")
                                .requestMatchers("/api/tic/**").hasAnyRole("RHG_TIC", "RHG_ADMIN")
                                .requestMatchers("/api/sig/**").hasAnyRole("RHG_SIG", "RHG_RRHH", "RHG_ADMIN")
                                .requestMatchers("/api/calendari/**").hasAnyRole("RHG_RRHH")
                                .requestMatchers("/api/cap/**").hasAnyRole("RHG_CAP")
                                .requestMatchers("/api/encarregat/**").hasAnyRole("RHG_ENCARREGAT")
                                .anyRequest().hasAnyRole("RHG_RRHH", "RHG_ADMIN")
                );
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
