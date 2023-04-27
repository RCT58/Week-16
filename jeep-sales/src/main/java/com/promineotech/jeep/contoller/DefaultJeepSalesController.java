package com.promineotech.jeep.contoller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DefaultJeepSalesController implements JeepSalesController {
  
  @Autowired
  private JeepSalesService jeepSalesService;
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<Jeep> fetchJeeps(
      @RequestParam JeepModel model, 
      @RequestParam String trim) {
    log.info("fetchJeeps called with model={} and trim={}", model, trim);
    return jeepSalesService.fetchJeeps(model, trim);
  }
  
}


