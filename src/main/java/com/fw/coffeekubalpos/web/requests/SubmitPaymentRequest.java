package com.fw.coffeekubalpos.web.requests;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitPaymentRequest {

  private UUID orderId;

}
