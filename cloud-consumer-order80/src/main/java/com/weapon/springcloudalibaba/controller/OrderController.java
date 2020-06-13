package com.weapon.springcloudalibaba.controller;

import com.weapon.springcloudalibaba.entities.CommonResult;
import com.weapon.springcloudalibaba.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    private static final String payment_url="http://CLOUD-PROVIDER-SERVICE/";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/add")
    public CommonResult<Payment> addPayment(Payment payment){
        log.info("log");
       return   restTemplate.postForObject(payment_url+"/payment/add",payment,CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        log.info("consumer");
        return restTemplate.getForObject(payment_url+"/payment/get/"+id,CommonResult.class);
    }

}
