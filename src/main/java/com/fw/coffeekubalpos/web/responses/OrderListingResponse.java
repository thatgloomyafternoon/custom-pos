package com.fw.coffeekubalpos.web.responses;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderListingResponse {

  private UUID orderId;
  private String customerName;
  private String displayedCreatedDate;
  private ZonedDateTime createdDate;

}
