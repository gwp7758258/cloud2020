package com.weapon.springcloudalibaba.service.impl;

import com.weapon.springcloudalibaba.dao.PaymentDao;
import com.weapon.springcloudalibaba.entities.Payment;
import com.weapon.springcloudalibaba.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int addPayment(Payment payment) {
        return paymentDao.addPayment(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentDao.getPayment(id);
    }

}
