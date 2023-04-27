package com.promineotech.jeep.service;

import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class DefaultJeepOrderService implements JeepOrderService {
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    
    return new Order();
  }
}
