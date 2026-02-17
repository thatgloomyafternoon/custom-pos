package com.fw.coffeekubalpos.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDTO {

  private String menuId;
  private Integer quantity;
  private String notes;

}
