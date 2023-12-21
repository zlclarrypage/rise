package com.rise;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 张牧之
 * @date 2022-12-03 19:28:28
 * @Email zhanglichang99@gmail.com
 */

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
        System.out.println("=================================GatewayApplication start success.=================================");
    }
}
