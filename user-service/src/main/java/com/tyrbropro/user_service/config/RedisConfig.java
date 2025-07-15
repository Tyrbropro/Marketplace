package com.tyrbropro.user_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "com.tyrbropro.user_service.repository.redis")
public class RedisConfig {
}
