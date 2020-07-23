package com.pym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/19 15:45
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
public class DashBoardServer {
    public static void main(String[] args) {
        SpringApplication.run(DashBoardServer.class,args);
    }
}
