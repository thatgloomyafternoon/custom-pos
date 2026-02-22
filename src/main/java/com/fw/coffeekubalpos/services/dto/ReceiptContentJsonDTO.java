package com.fw.coffeekubalpos.services.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptContentJsonDTO {

  private String cashierName;
  private String customerName;
  private String orderedDate;
  private String transactionDate;
  private List<ReceiptMenuJsonDTO> menuOrdered;
  private String totalPriceString;

}
