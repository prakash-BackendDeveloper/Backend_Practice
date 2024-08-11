package com.scaler.productservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigApplication {
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
