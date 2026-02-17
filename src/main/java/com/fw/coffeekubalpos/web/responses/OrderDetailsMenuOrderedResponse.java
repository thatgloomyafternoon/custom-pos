package com.fw.coffeekubalpos.web.responses;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsMenuOrderedResponse {

  private UUID orderMenuId;
  private String menuName;
  private String priceString;
  private Integer price;
  private Integer quantity;
  private String notes;


}
