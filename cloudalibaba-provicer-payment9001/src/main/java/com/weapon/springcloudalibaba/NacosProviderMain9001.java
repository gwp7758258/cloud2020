package com.weapon.springcloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderMain9001.class);
    }
}
