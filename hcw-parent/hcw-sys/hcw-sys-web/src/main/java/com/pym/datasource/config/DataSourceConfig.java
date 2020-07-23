package com.pym.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/3 23:24
 */
//@Configuration
public class DataSourceConfig {
    @ConfigurationProperties(prefix = "spring.read.datasource")
    @Bean(name = "readDataSource")
    public DruidDataSource readDataSource() {
        return new DruidDataSource();
    }
    @ConfigurationProperties(prefix = "spring.write.datasource")
    @Bean(name = "writeDataSource")
    public DruidDataSource writeDataSource() {
        return new DruidDataSource();
    }
}
