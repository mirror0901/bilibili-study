package com.keda.jpa.jpademo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class UserUpdateConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String name = "hyj";
        return Optional.ofNullable(name);
    }
}
