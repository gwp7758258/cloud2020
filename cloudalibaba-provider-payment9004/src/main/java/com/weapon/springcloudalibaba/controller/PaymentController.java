package com.weapon.springcloudalibaba.controller;

import com.weapon.springcloudalibaba.entities.CommonResult;
import com.weapon.springcloudalibaba.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"000000000000000001"));
        hashMap.put(2L,new Payment(2L,"000000000000000002"));
        hashMap.put(3L,new Payment(3L,"000000000000000003"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,"from mysql,serverPort:"+serverPort,payment);
        return result;
    }
}
