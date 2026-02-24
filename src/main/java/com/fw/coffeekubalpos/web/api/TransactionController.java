package com.fw.coffeekubalpos.web.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fw.coffeekubalpos.constants.ApiViewContracts;
import com.fw.coffeekubalpos.services.TransactionService;
import com.fw.coffeekubalpos.web.requests.SubmitPaymentRequest;
import com.fw.coffeekubalpos.web.responses.SubmitPaymentResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiViewContracts.API + ApiViewContracts.TRANSACTION)
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping(ApiViewContracts.SUBMIT_PAYMENT)
  public SubmitPaymentResponse submitPayment(@RequestBody SubmitPaymentRequest request) throws JsonProcessingException {
    return transactionService.submitPayment(request.getOrderId());
  }

}
