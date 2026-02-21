package com.fw.coffeekubalpos.services;

import com.fw.coffeekubalpos.services.dto.OrderDetailsDTO;
import com.fw.coffeekubalpos.web.responses.OrderCreateResponse;
import com.fw.coffeekubalpos.web.responses.OrderDeleteResponse;
import com.fw.coffeekubalpos.web.responses.OrderDetailsCreateResponse;
import com.fw.coffeekubalpos.web.responses.OrderDetailsResponse;
import com.fw.coffeekubalpos.web.responses.OrderListingResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

  List<OrderListingResponse> findAllOrders();
  OrderDetailsResponse findOrderDetailsByOrderId(UUID orderId);
  String findCustomerNameByOrderId(UUID orderId);
  OrderDetailsCreateResponse createNewOrderWithDetails(String customerName, List<OrderDetailsDTO> orderDetails);
  OrderCreateResponse createNewOrder(String customerName);
  OrderDeleteResponse deleteOrderByOrderId(UUID orderId);

}
