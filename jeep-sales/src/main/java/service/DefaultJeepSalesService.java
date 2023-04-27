package com.promineotech.jeep.service;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultJeepSalesService implements JeepSalesService {

  private final JeepRepository jeepRepository;

  public DefaultJeepSalesService(JeepRepository jeepRepository) {
    this.jeepRepository = jeepRepository;
  }

  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.info("Fetching Jeeps with model {} and trim {}", model, trim);
    return jeepRepository.fetchJeeps(model, trim);
  }
}
