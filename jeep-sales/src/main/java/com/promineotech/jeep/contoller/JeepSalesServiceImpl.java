package com.promineotech.jeep.contoller;

import com.promineotech.jeep.dao.JeepSalesDao;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JeepSalesServiceImpl implements JeepSalesService {

  private final JeepSalesDao jeepSalesDao;

  @Autowired
  public JeepSalesServiceImpl(JeepSalesDao jeepSalesDao) {
    this.jeepSalesDao = jeepSalesDao;
  }

  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    List<Jeep> jeeps = jeepSalesDao.fetchJeeps(model, trim);
    return jeeps;
  }
}
