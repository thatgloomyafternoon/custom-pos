package com.fw.coffeekubalpos.services;

import com.fw.coffeekubalpos.web.responses.OrderMenuCreateResponse;
import com.fw.coffeekubalpos.web.responses.OrderMenuDeleteResponse;
import java.util.UUID;

public interface OrderMenuService {

  OrderMenuCreateResponse createOrderMenu(UUID orderId, UUID menuId, Integer quantity, String notes);
  OrderMenuDeleteResponse deleteOrderMenuByOrderMenuId(String id);
  OrderMenuDeleteResponse deleteOrderMenusByOrderId(UUID orderId);

}
