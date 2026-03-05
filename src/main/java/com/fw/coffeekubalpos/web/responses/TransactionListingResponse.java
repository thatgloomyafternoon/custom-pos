package com.fw.coffeekubalpos.web.responses;

import com.fw.coffeekubalpos.services.dto.TransactionListingDTO;
import java.util.List;

@SuppressWarnings("unused")
public class TransactionListingResponse {

  private List<TransactionListingDTO> transactions;
  private Integer size;
  private Integer totalRevenue;
  private String displayedTotalRevenue;

  public List<TransactionListingDTO> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<TransactionListingDTO> transactions) {
    this.transactions = transactions;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Integer getTotalRevenue() {
    return totalRevenue;
  }

  public void setTotalRevenue(Integer totalRevenue) {
    this.totalRevenue = totalRevenue;
  }

  public String getDisplayedTotalRevenue() {
    return displayedTotalRevenue;
  }

  public void setDisplayedTotalRevenue(String displayedTotalRevenue) {
    this.displayedTotalRevenue = displayedTotalRevenue;
  }
}
