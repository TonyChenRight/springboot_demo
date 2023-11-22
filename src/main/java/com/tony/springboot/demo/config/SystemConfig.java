package com.tony.springboot.demo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Configuration
public class SystemConfig {
    @Value("#{${api.signature}}")
    private Map<String, String> apiSignatureMap;
}
