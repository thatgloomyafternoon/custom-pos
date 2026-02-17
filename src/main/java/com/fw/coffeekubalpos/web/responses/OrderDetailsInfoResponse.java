package com.fw.coffeekubalpos.web.responses;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsInfoResponse {

  private UUID orderId;
  private String customerName;
  private String createdDate;

}
