package com.example.students_management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;
    //dans cette methode on specifie comment spring chercher les roles des utilisateurs:
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       PasswordEncoder passwordEncoder=passwordEncoder();
        /*
        String encodedPasword=passwordEncoder.encode("12345");
        System.out.println(encodedPasword);
     auth.inMemoryAuthentication().withUser("user1").password(encodedPasword).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin12345")).roles("Admin");*/
      /*auth.jdbcAuthentication().dataSource(dataSource)
                //cette requète permet de charger l'tuilisateur:
                .usersByUsernameQuery("select username as principal,password as credentials,active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);*/
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").defaultSuccessUrl("/",false);
        http.csrf().disable();
        http.authorizeHttpRequests().antMatchers("/","/login/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/index/**","/addEtudiant/**","/delete/**","/save/**","/editEtudiant/**").hasAuthority("ADMIN");
        http.authorizeHttpRequests().antMatchers("/index/**").hasAuthority("USER");
        http.authorizeHttpRequests().antMatchers("/webjars/**").permitAll();
        http.exceptionHandling().accessDeniedPage("/403");
        http.authorizeHttpRequests().anyRequest().authenticated();

    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
