package com.final_case.DefineXPracticumFinalCase.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "aware")
public class AuditConfig {


    @Bean
    public AuditorAware<String> aware() {
        return () -> Optional.of("any user");
    }


}
