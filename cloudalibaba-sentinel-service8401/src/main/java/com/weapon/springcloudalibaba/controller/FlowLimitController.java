package com.weapon.springcloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class FlowLimitController {

    @RequestMapping("testA")
    public String testA(){
        return "testA-------";
    }

    @RequestMapping("testB")
    public String testB(){

        return "testB-------";
    }
    @RequestMapping("testBB")
    public String testBB(){
        log.info(Thread.currentThread().getName()+"testBB-------");
        return "testB-------";
    }

    @GetMapping("testC")
    public String testC(){
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "testC 测试RT-------";
    }
    @RequestMapping("testD")
    public String testD(){
        System.out.println("testD 异常比例测试");
        int age = 10/0;
        return "testD-------";
    }
    @RequestMapping("testE")
    public String testE(){
        System.out.println("testD 异常s数--测试");
        int age = 10/0;
        return "testD-------";
    }
    @RequestMapping("testHotKey")
    @SentinelResource(value = "testHotKey_rd",blockHandler="deal_testHotKet")
    public String testHotKey(
            @RequestParam(value = "p1",required = false) String p1,
            @RequestParam(value = "p2",required = false) String p2){
        return "testHotKey-------";
    }

    public String deal_testHotKet(String p1, String p2, BlockException e){
        return "deal_testHotKet-------/(ㄒoㄒ)/~~";
    }
}
