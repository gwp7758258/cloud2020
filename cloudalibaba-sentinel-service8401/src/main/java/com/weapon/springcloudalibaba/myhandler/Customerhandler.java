package com.weapon.springcloudalibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.weapon.springcloudalibaba.entities.CommonResult;
import com.weapon.springcloudalibaba.entities.Payment;

public class Customerhandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444,"按客户自定义，global handlerException",new Payment(2020L,"serial003"));
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444,"按客户自定义，global handlerException2",new Payment(2020L,"serial003"));
    }
}
