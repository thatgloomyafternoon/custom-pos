package com.fw.coffeekubalpos.web.requests;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderMenuCreateRequest {

  private UUID orderId;
  private UUID menuId;
  private Integer quantity;
  private String notes;

}
