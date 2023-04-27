package com.promineotech.jeep.service;

import com.promineotech.jeep.dao.JeepOrderDao;
import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultJeepOrderService implements JeepOrderService {

  @Autowired
  private JeepOrderDao jeepOrderDao;

  @Override
  @Transactional
  public Order createOrder(OrderRequest orderRequest) {


    Customer customer = getCustomer(orderRequest);
    JeepModel model = getModel(orderRequest);
    Color color = getColor(orderRequest);
    Engine engine = getEngine(orderRequest);
    Tire tire = getTire(orderRequest);
    List<Option> options = getOptions(orderRequest);

    BigDecimal totalPrice = model.getBasePrice()
                            .add(color.getPrice())
                            .add(engine.getPrice())
                            .add(tire.getPrice());

    for (Option option : options) {
      totalPrice = totalPrice.add(option.getPrice());
    }

    Order order = jeepOrderDao.saveOrder(customer, model, color, engine, tire, options, totalPrice);

    return order;
  }


}
