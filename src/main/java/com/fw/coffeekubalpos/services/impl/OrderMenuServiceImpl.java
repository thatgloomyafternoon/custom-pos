package com.fw.coffeekubalpos.services.impl;

import com.fw.coffeekubalpos.entities.Menu;
import com.fw.coffeekubalpos.entities.Order;
import com.fw.coffeekubalpos.entities.OrderMenu;
import com.fw.coffeekubalpos.repositories.OrderMenuRepository;
import com.fw.coffeekubalpos.services.OrderMenuService;
import com.fw.coffeekubalpos.web.responses.OrderMenuCreateResponse;
import com.fw.coffeekubalpos.web.responses.OrderMenuDeleteResponse;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrderMenuServiceImpl implements OrderMenuService {

  private final OrderMenuRepository orderMenuRepository;

  public OrderMenuServiceImpl(OrderMenuRepository orderMenuRepository) {
    this.orderMenuRepository = orderMenuRepository;
  }

  @Override
  public OrderMenuCreateResponse createOrderMenu(UUID orderId, UUID menuId, Integer quantity, String notes) {
    Order order = new Order(orderId);
    Menu menu = new Menu(menuId);
    OrderMenu newOrderMenu = new OrderMenu();
    newOrderMenu.setOrder(order);
    newOrderMenu.setMenu(menu);
    newOrderMenu.setQuantity(quantity);
    newOrderMenu.setNotes(notes);
    orderMenuRepository.save(newOrderMenu);
    OrderMenuCreateResponse response = new OrderMenuCreateResponse();
    response.setMessage("CREATE SUCCESS");
    return response;
  }

  @Override
  public OrderMenuDeleteResponse deleteOrderMenuByOrderMenuId(String id) {
    UUID uuid = UUID.fromString(id);
    orderMenuRepository.deleteById(uuid);
    OrderMenuDeleteResponse response = new OrderMenuDeleteResponse();
    response.setMessage("DELETE SUCCESS");
    return response;
  }

  @Override
  public OrderMenuDeleteResponse deleteOrderMenusByOrderId(UUID orderId) {
    orderMenuRepository.deleteAllByOrderId(orderId);
    OrderMenuDeleteResponse response = new OrderMenuDeleteResponse();
    response.setMessage("DELETE BY ORDER ID SUCCESS");
    return response;
  }

}
