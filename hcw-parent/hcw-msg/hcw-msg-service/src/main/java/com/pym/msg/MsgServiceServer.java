package com.pym.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.jms.annotation.EnableJms;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 17:14
 */
@SpringBootApplication
@EnableJms
@EnableDiscoveryClient
@EnableCaching
public class MsgServiceServer {
    public static void main(String[] args) {
        SpringApplication.run(MsgServiceServer.class, args);
    }
}
