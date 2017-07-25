package com.ding.buyaround.security;

import com.ding.buyaround.security.jwt.JWTConfigurer;
import com.ding.buyaround.security.jwt.TokenProvider;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created by pc on 2017/6/1.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf()
                .disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //.httpBasic() // optional, if you want to access
                //  .and()     // the services from a browser
                .authorizeRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/public").permitAll()
                .antMatchers("/category/getAll").permitAll()
                .antMatchers("/oss/getToken").permitAll()
                .antMatchers("/oss/callback").permitAll()
                .antMatchers("/stock/putPOI").permitAll()
                .antMatchers("/ws/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JWTConfigurer(this.tokenProvider));
        // @formatter:on
    }

}
