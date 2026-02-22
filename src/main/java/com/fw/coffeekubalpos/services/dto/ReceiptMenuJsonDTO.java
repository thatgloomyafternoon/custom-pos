package com.fw.coffeekubalpos.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptMenuJsonDTO {

  private String menuName;
  private Integer price;
  private String priceString;
  private Integer quantity;
  private String notes;

}
