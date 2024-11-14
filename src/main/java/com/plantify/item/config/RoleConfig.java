package com.plantify.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoleConfig {

    @Bean
    public List<String> adminRoles() {
        return List.of("MANAGER", "ADMIN");
    }

}
