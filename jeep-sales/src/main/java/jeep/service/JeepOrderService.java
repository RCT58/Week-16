package com.promineotech.jeep.service;

import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.entity.Order;

public interface JeepOrderService {
  Order createOrder(OrderRequest orderRequest);
}
