package com.lmy.gradle.elsa;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author LMinY
 * @since 2020/7/8
 */
@Data
@Validated
@ConfigurationProperties(prefix = "hof.elsa")
public class ElsaProperties {
    private boolean enabled;
    private Env env;

    /**
     * 缓存最大值
     */
    @Min(500)
    @Max(2000)
    private int buffer = 1000;
}
