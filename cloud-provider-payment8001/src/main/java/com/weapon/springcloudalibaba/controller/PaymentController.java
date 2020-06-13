package com.weapon.springcloudalibaba.controller;

import com.weapon.springcloudalibaba.entities.CommonResult;
import com.weapon.springcloudalibaba.entities.Payment;
import com.weapon.springcloudalibaba.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String servicePort;

    @PostMapping("/add")
    public CommonResult addPayment(@RequestBody Payment payment){
        int result=paymentService.addPayment(payment);

        //log.info("insert into "+result);
        if(result>0){
            return new CommonResult(200,"success,servicePort:"+servicePort,result);
        }else {
            return new CommonResult(400,"fail",null);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        Payment result=paymentService.getPayment(id);
        if(result!=null){
            return new CommonResult(200,"success,servicePort:"+servicePort,result);
        }else {
            return new CommonResult(400,"fail",null);
        }
    }

}
