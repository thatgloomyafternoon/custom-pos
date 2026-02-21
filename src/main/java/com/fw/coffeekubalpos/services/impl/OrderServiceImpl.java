package com.fw.coffeekubalpos.services.impl;

import com.fw.coffeekubalpos.constants.PropertyConstants;
import com.fw.coffeekubalpos.entities.Menu;
import com.fw.coffeekubalpos.entities.Order;
import com.fw.coffeekubalpos.entities.OrderMenu;
import com.fw.coffeekubalpos.repositories.OrderMenuRepository;
import com.fw.coffeekubalpos.repositories.OrderRepository;
import com.fw.coffeekubalpos.services.OrderService;
import com.fw.coffeekubalpos.services.dto.OrderDetailsDTO;
import com.fw.coffeekubalpos.utils.CurrencyStringFormatUtil;
import com.fw.coffeekubalpos.utils.MapperUtil;
import com.fw.coffeekubalpos.utils.PriceUtil;
import com.fw.coffeekubalpos.web.responses.OrderCreateResponse;
import com.fw.coffeekubalpos.web.responses.OrderDeleteResponse;
import com.fw.coffeekubalpos.web.responses.OrderDetailsCreateResponse;
import com.fw.coffeekubalpos.web.responses.OrderDetailsInfoResponse;
import com.fw.coffeekubalpos.web.responses.OrderDetailsMenuOrderedResponse;
import com.fw.coffeekubalpos.web.responses.OrderDetailsResponse;
import com.fw.coffeekubalpos.web.responses.OrderListingResponse;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final MapperUtil mapperUtil;
  private final CurrencyStringFormatUtil currencyStringFormatUtil;
  private final OrderRepository orderRepository;
  private final OrderMenuRepository orderMenuRepository;

  public OrderServiceImpl(MapperUtil mapperUtil,
                           CurrencyStringFormatUtil currencyStringFormatUtil,
                           OrderRepository orderRepository,
                           OrderMenuRepository orderMenuRepository) {
    this.mapperUtil = mapperUtil;
    this.currencyStringFormatUtil = currencyStringFormatUtil;
    this.orderRepository = orderRepository;
    this.orderMenuRepository = orderMenuRepository;
  }

  @Override
  public List<OrderListingResponse> findAllOrders() {
    List<OrderListingResponse> response = orderRepository.findAll()
      .stream()
      .map(mapperUtil::mapOrderToOrderListingResponse)
      .sorted(Comparator.comparing(OrderListingResponse::getCreatedDate))
      .collect(Collectors.toList());
    /** convert time zone */
    response.forEach(o -> o.setCreatedDate(o.getCreatedDate().plusHours(7)));
    response.forEach(o -> o.setDisplayedCreatedDate(o.getCreatedDate().format(DateTimeFormatter.ofPattern(PropertyConstants.DATE_TIME_PATTERN))));
    /** */
    return response;
  }

  @Override
  public OrderDetailsResponse findOrderDetailsByOrderId(UUID orderId) {
    /**
     * this call below will return Order object with the populated
     * Set of OrderMenu, with each already has the populated Menu object
     */
    Order order = orderRepository.findOrderDetailsById(orderId);
    List<OrderDetailsMenuOrderedResponse> menuOrdered = new ArrayList<>();
    int totalPrice = 0;
    if(order.getOrderMenu() != null) {
      menuOrdered = order.getOrderMenu()
        .stream()
        .map(mapperUtil::mapOrderMenuToOrderDetailsMenuOrderedResponse)
        .sorted(Comparator.comparing(OrderDetailsMenuOrderedResponse::getMenuName))
        .collect(Collectors.toList());
      menuOrdered.forEach(mo -> mo.setPriceString(currencyStringFormatUtil.format(mo.getPrice())));
      totalPrice = PriceUtil.calculateTotalPrice(menuOrdered);
    }
    OrderDetailsInfoResponse odir = mapperUtil.mapOrderToOrderDetailsInfoResponse(order);
    /** convert time zone */
    odir.setCreatedDate(order.getCreatedDate().plusHours(7).format(DateTimeFormatter.ofPattern(PropertyConstants.DATE_TIME_PATTERN)));
    /** */
    OrderDetailsResponse odr = new OrderDetailsResponse();
    odr.setInfo(odir);
    odr.setMenuOrdered(menuOrdered);
    odr.setTotalPrice(totalPrice);
    odr.setTotalPriceString(currencyStringFormatUtil.format(totalPrice));
    return odr;
  }

  @Override
  public String findCustomerNameByOrderId(UUID orderId) {
    return orderRepository.findById(orderId).get().getCustomerName();
  }

  @Override
  public OrderDetailsCreateResponse createNewOrderWithDetails(String customerName, List<OrderDetailsDTO> orderDetails) {
    Order newOrder = new Order();
    newOrder.setCustomerName(customerName);
    newOrder = orderRepository.save(newOrder);
    List<OrderMenu> orderMenus = new ArrayList<>();
    for(int i = 0; i < orderDetails.size(); i++) {
      OrderDetailsDTO orderDetailsDto = orderDetails.get(i);
      Order order = new Order(newOrder.getId());
      Menu menu = new Menu(UUID.fromString(orderDetailsDto.getMenuId()));
      OrderMenu orderMenu = new OrderMenu();
      orderMenu.setOrder(order);
      orderMenu.setMenu(menu);
      orderMenu.setQuantity(orderDetailsDto.getQuantity());
      orderMenu.setNotes(orderDetailsDto.getNotes());
      orderMenus.add(orderMenu);
    }
    orderMenuRepository.saveAllAndFlush(orderMenus);
    OrderDetailsCreateResponse response = new OrderDetailsCreateResponse();
    response.setMessage("CREATE NEW ORDER WITH DETAILS SUCCESS");
    return response;
  }

  @Override
  public OrderCreateResponse createNewOrder(String customerName) {
    Order newOrder = new Order();
    newOrder.setCustomerName(customerName);
    orderRepository.save(newOrder);
    OrderCreateResponse response = new OrderCreateResponse();
    response.setMessage("CREATE NEW ORDER SUCCESS");
    return response;
  }

  @Override
  public OrderDeleteResponse deleteOrderByOrderId(UUID orderId) {
    OrderDeleteResponse response = new OrderDeleteResponse();
    Order order = orderRepository.findOrderDetailsById(orderId);
    if(order.getOrderMenu().size() > 0) {
      throw new IllegalArgumentException("Cannot delete non-empty order");
    } else {
      response.setMessage("DELETE SUCCESS");
      orderRepository.delete(order);
    }
    return response;
  }

}
