package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JeepSalesControllerTest {

  @Test
  void fetchJeeps_returnsExpectedJeeps() {
    // Given
    JeepSalesController jeepSalesController = new DefaultJeepSalesController(/*dependencies*/);
    List<Jeep> expectedJeeps = createExpectedJeepList();

    // When
    List<Jeep> actualJeeps = jeepSalesController.fetchJeeps(JeepModel.WRANGLER, "Sport");

    // Then
    assertThat(actualJeeps).isEqualTo(expectedJeeps);
  }

  private List<Jeep> createExpectedJeepList() {
    Jeep jeep1 = Jeep.builder()
      .model(JeepModel.WRANGLER)
      .trim("Sport")
      .numDoors(4)
      .wheelSize(17)
      .basePrice(35000)
      .build();

    Jeep jeep2 = Jeep.builder()
      .model(JeepModel.CHEROKEE)
      .trim("Limited")
      .numDoors(4)
      .wheelSize(18)
      .basePrice(45000)
      .build();

    return List.of(jeep1, jeep2);
  }
}
