package com.spring.security.oath.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requestMatchers()
                .antMatchers("/login", "/oauth/authorize")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin() // uses default /login page (if we want custom use .loginPage("myPage");
                .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	// Use an in-memory user store, not database, not custom service

        auth.parentAuthenticationManager(authenticationManager)
                .inMemoryAuthentication()
                .withUser("ramu")
                .password("ramu")
                .roles("USER");
    }
}

/*
That's the point where Spring decides:
customLoginPage == false  -> generate default /login page

customLoginPage == true   -> don't generate it
_______________________________________________________________________________

FormLoginConfigurer
        ↓
AbstractAuthenticationFilterConfigurer
        ↓
customLoginPage flag
        ↓
DefaultLoginPageConfigurer
        ↓
DefaultLoginPageGeneratingFilter (or not)

 */
