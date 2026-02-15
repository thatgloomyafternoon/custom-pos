package com.fw.coffeekubalpos.web.api;

import com.fw.coffeekubalpos.constants.ApiViewContracts;
import com.fw.coffeekubalpos.services.OrderService;
import com.fw.coffeekubalpos.web.requests.OrderCreateRequest;
import com.fw.coffeekubalpos.web.requests.OrderDeleteRequest;
import com.fw.coffeekubalpos.web.requests.OrderDetailsCreateRequest;
import com.fw.coffeekubalpos.web.responses.OrderCreateResponse;
import com.fw.coffeekubalpos.web.responses.OrderDeleteResponse;
import com.fw.coffeekubalpos.web.responses.OrderDetailsCreateResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiViewContracts.API + ApiViewContracts.ORDER)
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping(ApiViewContracts.CREATE)
  public OrderCreateResponse create(@Valid @RequestBody OrderCreateRequest request) {
    return orderService.createNewOrder(request.getCustomerName());
  }

  @PostMapping(ApiViewContracts.CREATE_DETAILS)
  public OrderDetailsCreateResponse createDetails(@Valid @RequestBody OrderDetailsCreateRequest request) {
    return orderService.createNewOrderWithDetails(request.getCustomerName(), request.getOrderDetails());
  }

  @PostMapping(ApiViewContracts.DELETE)
  public OrderDeleteResponse delete(@RequestBody OrderDeleteRequest request) {
    return orderService.deleteOrderByOrderId(request.getOrderId());
  }

}
