package com.promineotech.jeep.entity;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class OrderRequest {
  @NotBlank
  private String customer;

  @NotNull
  private JeepModel model;

  @NotNull
  @Length(min = 2, max = 2)
  private String trim;

  @NotNull
  @Positive
  private int doors;

  @NotBlank
  private String color;

  @NotBlank
  private String engine;

  @NotBlank
  private String tire;

  @NotNull
  private List<String> options;
}
