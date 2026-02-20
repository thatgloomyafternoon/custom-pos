package com.fw.coffeekubalpos.web.responses;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsResponse {

  private OrderDetailsInfoResponse info;
  private List<OrderDetailsMenuOrderedResponse> menuOrdered;
  private Integer totalPrice;
  private String totalPriceString;

}
