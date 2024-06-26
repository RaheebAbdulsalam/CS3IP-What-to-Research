package com.cs3ip.whattoresearch.config;

import com.cs3ip.whattoresearch.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * Configuration class for web security settings.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Defines a custom UserDetailsService bean.
     *
     * @return An instance of CustomUserDetailsService.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    /**
     * Defines a BCryptPasswordEncoder bean.
     *
     * @return An instance of BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines a DaoAuthenticationProvider bean.
     *
     * @return An instance of DaoAuthenticationProvider.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configures HTTP security settings.
     *
     * @param http An HttpSecurity object to configure security settings.
     * @return A SecurityFilterChain object.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/profile").authenticated()
                .requestMatchers("/research/research-form").authenticated()
                .requestMatchers("/research/search").authenticated()
                .requestMatchers("/research/all-projects").authenticated()
                .requestMatchers("/research/display/{id}").authenticated()
                .requestMatchers("api/v1/chat-bot").authenticated()
                // ONLY ADMINS CAN ACCESS ADMIN PAGES
                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin(login -> login.loginPage("/login")
                        .usernameParameter("email").passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())

                .csrf(csrf -> csrf
                        .csrfTokenRepository(new org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository())
                );

        return http.build();
    }



}
