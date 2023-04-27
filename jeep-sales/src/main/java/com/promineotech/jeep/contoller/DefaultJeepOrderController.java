package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.service.JeepOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultJeepOrderController implements JeepOrderController {

  private static final Logger LOG = LoggerFactory.getLogger(DefaultJeepOrderController.class);

  @Autowired
  private JeepOrderService jeepOrderService;

  @Override
  public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
    LOG.info("OrderRequest: {}", orderRequest);
    Order order = jeepOrderService.createOrder(orderRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(order);
  }
}
