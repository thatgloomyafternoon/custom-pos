package com.fw.coffeekubalpos.web.responses;

import com.fw.coffeekubalpos.services.dto.TransactionListingDTO;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionListingResponse {

  private List<TransactionListingDTO> transactions;
  private Integer size;
  private Integer totalRevenue;
  private String displayedTotalRevenue;

}
