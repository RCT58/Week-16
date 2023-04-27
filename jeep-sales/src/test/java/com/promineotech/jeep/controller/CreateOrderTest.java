package com.promineotech.jeep.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.promineotech.jeep.entity.Order;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:db/schema.sql", "classpath:db/data.sql"})
public class CreateOrderTest {
  
  @LocalServerPort
  private int serverPort;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testCreateOrderReturnsSuccess201() {
    String body = createOrderBody();
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);

    String uri = String.format("http://localhost:%d/orders", serverPort);
    ResponseEntity<Order> response = restTemplate.exchange(uri, HttpMethod.POST, bodyEntity, Order.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();

    // Add assertions for the Order object as shown in the initial instructions
  }

  private String createOrderBody() {
    return "{"
        + "  \"customer\":\"MORISON_LINA\","
        + "  \"model\":\"WRANGLER\","
        + "  \"trim\":\"Sport Altitude\","
        + "  \"doors\":4,"
        + "  \"color\":\"EXT_NACHO\","
        + "  \"engine\":\"2_0_TURBO\","
        + "  \"tire\":\"35_TOYO\","
        + "  \"options\":["
        + "    \"DOOR_QUAD_4\","
        + "    \"EXT_AEV_LIFT\","
        + "    \"EXT_WARN_WINCH\","
        + "    \"EXT_WARN_BUMPER_FRONT\","
        + "    \"EXT_WARN_BUMPER_REAR\","
        + "    \"EXT_ARB_COMPRESSOR\""
        + "  ]"
        + "}";
  }
}
