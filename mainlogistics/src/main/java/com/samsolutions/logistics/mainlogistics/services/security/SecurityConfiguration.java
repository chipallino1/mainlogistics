package com.samsolutions.logistics.mainlogistics.services.security;


import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private ContactsRepository contactsRepository;
    private FirmsRepository firmsRepository;
    private DataSource dataSource;
    private SaltHash saltHash;
    @Value("${spring.queries.users-query}")
    private String usersQuery;
    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }
    @Autowired
    public void setFirmsRepository(FirmsRepository firmsRepository) {
        this.firmsRepository = firmsRepository;
    }
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
                .usersByUsernameQuery(usersQuery)
                .dataSource(dataSource)
                .passwordEncoder(saltHash);
    }

}
