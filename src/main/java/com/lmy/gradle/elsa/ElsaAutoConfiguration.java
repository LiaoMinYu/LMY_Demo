package com.lmy.gradle.elsa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author LMinY
 * @since 2020/7/8
 */
@Configuration
@ConditionalOnClass(ElsaContext.class)
@EnableConfigurationProperties({ElsaProperties.class})
public class ElsaAutoConfiguration {
    @Resource
    private ElsaProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "hof.elsa", value = "enabled", havingValue = "true")
    public Elsa elsa() {
        return new ElsaContext(properties);
    }
}
