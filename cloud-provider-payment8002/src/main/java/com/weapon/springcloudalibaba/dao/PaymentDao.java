package com.weapon.springcloudalibaba.dao;

import com.weapon.springcloudalibaba.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int addPayment(Payment payment);

    Payment getPayment(@Param("id") Long id);
}
