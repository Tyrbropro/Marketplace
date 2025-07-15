package com.tyrbropro.user_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories(basePackages = "com.tyrbropro.user_service.repository.jdbc")
public class JdbcConfig {
}
