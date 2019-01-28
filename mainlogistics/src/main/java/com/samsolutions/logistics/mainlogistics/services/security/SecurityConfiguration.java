package com.samsolutions.logistics.mainlogistics.services.security;


import com.samsolutions.logistics.mainlogistics.services.security.SaltHashEncoder.SaltHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Security config
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private SaltHash saltHash;
    @Value("${spring.queries.users-query}")
    private String usersQuery;
    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setPasswordEncoder(SaltHash saltHash) {
        this.saltHash = saltHash;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usersQuery)
                //.usersByUsernameQuery(usersFirmsQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                //.authoritiesByUsernameQuery(rolesFirmsQuery)
                .passwordEncoder(saltHash) ;
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // .csrf()
                // .and()
                // .requiresChannel()
                // .antMatchers("/auth").requiresSecure()
                //.and()
                .authorizeRequests()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/routes/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/auth")
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }
}
