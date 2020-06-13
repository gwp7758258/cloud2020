package com.weapon.springcloudalibaba.service;

import com.weapon.springcloudalibaba.entities.Payment;

public interface PaymentService {

    int addPayment(Payment payment);

    Payment getPayment(Long id);
}
