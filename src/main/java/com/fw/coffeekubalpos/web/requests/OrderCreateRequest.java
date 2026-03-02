package com.fw.coffeekubalpos.web.requests;

import javax.validation.constraints.NotNull;

public class OrderCreateRequest {

  @NotNull
  private String customerName;

  public @NotNull String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(@NotNull String customerName) {
    this.customerName = customerName;
  }
}
