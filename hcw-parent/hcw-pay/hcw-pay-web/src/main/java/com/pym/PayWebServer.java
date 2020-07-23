package com.pym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/18 23:15
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PayWebServer {
    public static void main(String[] args) {
        SpringApplication.run(PayWebServer.class, args);
    }
}
