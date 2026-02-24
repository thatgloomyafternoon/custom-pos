package com.fw.coffeekubalpos.services.dto;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("unused")
@Getter
@Setter
public class TransactionListingDTO {

  private UUID transactionId;
  private String customerName;
  private String displayedTransactionDate;
  private ZonedDateTime transactionDate;
  private Integer totalPrice;
  private String totalPriceString;

}
