package com.renmoney.bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.renmoney.bookstore.security.ApplicationUserPermission.*;
import static com.renmoney.bookstore.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    private final String [] WHITELIST = {
            "/",
            "/css/*",
            "/js/*"
    };

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                .antMatchers(WHITELIST).permitAll()
                .antMatchers("/api/**").hasAnyRole(NORMAL.name(), ADMIN_ASSISTANT.name(), ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
//                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails adamsUser = User.builder()
                .username("adams")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails mercyUser = User.builder()
                .username("mercy")
                .password(passwordEncoder.encode("password"))
//                .roles(NORMAL.name())
                .authorities(NORMAL.getGrantedAuthorities())
                .build();


        UserDetails promiseUser = User.builder()
                .username("promise")
                .password(passwordEncoder.encode("password"))
                .authorities(ADMIN_ASSISTANT.getGrantedAuthorities())
//                .roles(NORMAL.name())
                .build();

        return new InMemoryUserDetailsManager(
                mercyUser,
                promiseUser,
                adamsUser
        );
    }
}
