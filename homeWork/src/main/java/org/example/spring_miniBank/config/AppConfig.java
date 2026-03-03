package org.example.spring_miniBank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.example.spring_miniBank")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
