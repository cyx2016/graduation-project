/*
package com.cyx.common;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.cyx.mapper")
public class DataSourceConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "jdbc.ds")
    public DataSource readDataSource() {
        return new DruidDataSource();
    }
}
*/
