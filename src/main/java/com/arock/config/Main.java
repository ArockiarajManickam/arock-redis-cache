package com.arock.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * Main entrance for micro-service.
 *
 * @author Arockiaraj Manickam
 */

@SpringBootApplication(scanBasePackages = { "com.arock"})
@EnableCaching
public class Main {

    public static void main(final String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
