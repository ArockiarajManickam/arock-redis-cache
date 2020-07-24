package com.arock.config;

import com.arock.core.PackageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Basic application context for scanning service, dao and entity layers.
 *
 * @author Arockiaraj Manickam
 */
@ComponentScan(basePackageClasses = PackageInfo.class)
@Configuration
public class ApplicationContext {

    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();      
    }
}
