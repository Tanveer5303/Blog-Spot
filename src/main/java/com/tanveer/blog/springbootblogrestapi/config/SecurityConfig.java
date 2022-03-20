package com.tanveer.blog.springbootblogrestapi.config;

import com.tanveer.blog.springbootblogrestapi.Security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //enable method level security When ever we are providing method level security we use this annotation.if we dont give it will throw exceptions like bad credentials
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/post/**").permitAll()  //this HttpMethods will not require auth
                .antMatchers(HttpMethod.GET,"/comment/**").permitAll()  //this HttpMethods will not require auth
                .antMatchers(HttpMethod.POST,"/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    //password encoder
    //we can not pass the simple string as password in userdetails so we have to encyrpt it
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //in Database
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    //    //Create in memory users
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//      UserDetails ali =  User.builder()
//                .username("ali")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//      UserDetails admin = User.builder()
//              .username("admin")
//              .password(passwordEncoder().encode("admin"))
//              .roles("ADMIN")
//              .build();
//    return new InMemoryUserDetailsManager(ali,admin);
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
