package com.example.springsession.demosession;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRedisHttpSession
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {
}