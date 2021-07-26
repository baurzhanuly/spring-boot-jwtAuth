package com.example.springbootjwtAuth.shared.security;

import com.example.springbootjwtAuth.jwt.JwtTokenAuthorizationOncePerRequestFilter;
import com.example.springbootjwtAuth.jwt.JwtUnAuthorizedResponseAuthenticationEntryPoint;
import com.example.springbootjwtAuth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JWTWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private JwtUnAuthorizedResponseAuthenticationEntryPoint unAuthorizedResponseAuthenticationEntryPoint;

    private JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter;

    @Autowired
    public JWTWebSecurityConfig(UserService userService,
                                BCryptPasswordEncoder bCryptPasswordEncoder,
                                JwtUnAuthorizedResponseAuthenticationEntryPoint unAuthorizedResponseAuthenticationEntryPoint,
                                JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.unAuthorizedResponseAuthenticationEntryPoint = unAuthorizedResponseAuthenticationEntryPoint;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().
                authorizeRequests().
                antMatchers(HttpMethod.POST,"/authenticate").permitAll().
                anyRequest().authenticated()
                .and().
                exceptionHandling().authenticationEntryPoint(unAuthorizedResponseAuthenticationEntryPoint).and().sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.cors().and().csrf().disable();
    }

}
