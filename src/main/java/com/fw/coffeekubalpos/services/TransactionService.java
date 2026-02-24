package com.fw.coffeekubalpos.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fw.coffeekubalpos.services.dto.ReceiptContentJsonDTO;
import com.fw.coffeekubalpos.web.responses.SubmitPaymentResponse;
import com.fw.coffeekubalpos.web.responses.TransactionListingResponse;
import java.util.UUID;

public interface TransactionService {

  SubmitPaymentResponse submitPayment(UUID orderId) throws JsonProcessingException;
  TransactionListingResponse getTransactionHistory();
  TransactionListingResponse getTransactionHistory(String customerName, String year, String month, String date);
  ReceiptContentJsonDTO getReceiptContentByTransactionId(UUID transactionId) throws JsonProcessingException, JsonMappingException;

}
