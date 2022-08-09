package com.lmy.gradle.config;

/**
 * @author LMinY
 * @description
 * @since 2022-07-07
 */

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LMinY
 * @description: 开启自动刷新XML
 * @date 2020/8/27
 */
@Configuration
public class XMLMapperConfig {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 根据配置文件的值 是否开启实时刷新
     */
    @Value("${XMLMapperRefresh}")
    Boolean XMLMapperRefresh;

    @Bean
    public void xMLMapperLoader() {
        if (XMLMapperRefresh) {
            new XMLMapperLoader(sqlSessionFactory, "/mapper");
        }
    }
}