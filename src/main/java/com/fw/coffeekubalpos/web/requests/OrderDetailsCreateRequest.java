package com.fw.coffeekubalpos.web.requests;

import com.fw.coffeekubalpos.services.dto.OrderDetailsDTO;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsCreateRequest {

  private String customerName;
  private List<OrderDetailsDTO> orderDetails;

}
