package com.pym.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/18 21:10
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class PayServiceServer {
    public static void main(String[] args) {
        SpringApplication.run(PayServiceServer.class, args);
    }
}
