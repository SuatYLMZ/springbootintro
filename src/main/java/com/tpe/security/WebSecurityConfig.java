package com.tpe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // to make this is configuration class
@EnableWebSecurity //enabling security
//@EnableGlobalMethodSecurity(prePostEnabled = true) //security will be enabled in method base
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /*
        1. AuthenticationManager
        2. AuthenticationProvider
        3. PassEncode
     */

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(). //Disable Cross Site Request Forgery
                authorizeHttpRequests(). //check requests if they are authorized
                antMatchers("/", "/index.html", "/css/*", "/js/*", "/register").permitAll(). //give permission to these paths-->no authenticatio/authorization
                and().
                authorizeRequests().antMatchers("/students/**").hasRole("ADMIN"). //permit all requests with path "/students/" any one who has ADMIN role
                anyRequest(). //except other requests
                authenticated(). //authenticate
                and().
                httpBasic(); //use Basic Authentication for this
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10); //number between 4-31(4 will be weakest,
        // 31 is strongest (will take time, energy) so normally middle around 10 is enough
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider autoProvider = new DaoAuthenticationProvider();
        autoProvider.setPasswordEncoder(passwordEncoder());
        autoProvider.setUserDetailsService(userDetailsService);
        return autoProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
