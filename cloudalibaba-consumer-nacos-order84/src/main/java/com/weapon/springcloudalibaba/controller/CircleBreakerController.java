package com.weapon.springcloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.weapon.springcloudalibaba.entities.CommonResult;
import com.weapon.springcloudalibaba.entities.Payment;
import com.weapon.springcloudalibaba.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    @Value("${server.port}")
    private String serverPort;

    @Value("${server-url.nacos-user-service}")
    private String SERVICE_URL;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")  //没有配置
//    @SentinelResource(value = "fallback",fallback = "handlerFallback")  //fallback只负责业务异常
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler") //blockHandler只负责sentine控制台配置违规
//    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler") //handlerFallback和blockHandler都配置
    @SentinelResource(value = "fallback",
            fallback = "handlerFallback",
            blockHandler = "blockHandler",
            exceptionsToIgnore = IllegalAccessException.class) //exceptionsToIgnore配置
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id, CommonResult.class,id);
        if (id==4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return result;
    }
    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"兜底异常handlerFallback,exception内容"+e.getMessage(),payment);
    }

    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException exception) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(445,"blockHandler,exception内容"+exception.getMessage(),payment);
    }
    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }


}
