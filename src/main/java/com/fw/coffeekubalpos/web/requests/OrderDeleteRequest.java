package com.fw.coffeekubalpos.web.requests;

import java.util.UUID;

public class OrderDeleteRequest {

  private UUID orderId;

  public UUID getOrderId() {
    return orderId;
  }

  public void setOrderId(UUID orderId) {
    this.orderId = orderId;
  }
}
