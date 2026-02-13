package com.fw.coffeekubalpos.utils;

import com.fw.coffeekubalpos.entities.Menu;
import com.fw.coffeekubalpos.entities.Order;
import com.fw.coffeekubalpos.entities.OrderMenu;
import com.fw.coffeekubalpos.entities.Transaction;
import com.fw.coffeekubalpos.services.dto.MenuDropdownDTO;
import com.fw.coffeekubalpos.services.dto.ReceiptMenuJsonDTO;
import com.fw.coffeekubalpos.services.dto.TransactionListingDTO;
import com.fw.coffeekubalpos.web.responses.MenuListingResponse;
import com.fw.coffeekubalpos.web.responses.MenuUpdateViewResponse;
import com.fw.coffeekubalpos.web.responses.OrderDetailsInfoResponse;
import com.fw.coffeekubalpos.web.responses.OrderDetailsMenuOrderedResponse;
import com.fw.coffeekubalpos.web.responses.OrderListingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class MapperUtil {

  @Mapping(source = "order.id", target = "orderId")
  @Mapping(source = "order.customerName", target = "customerName")
  @Mapping(source = "order.createdDate", target = "createdDate")
  @Mapping(target = "displayedCreatedDate", ignore = true)
  public abstract OrderListingResponse mapOrderToOrderListingResponse(Order order);

  @Mapping(source = "om.id", target = "orderMenuId")
  @Mapping(source = "om.menu.name", target = "menuName")
  @Mapping(source = "om.menu.price", target = "price")
  @Mapping(source = "om.quantity", target = "quantity")
  @Mapping(source = "om.notes", target = "notes")
  @Mapping(target = "priceString", ignore = true)
  public abstract OrderDetailsMenuOrderedResponse mapOrderMenuToOrderDetailsMenuOrderedResponse(OrderMenu om);

  @Mapping(source = "o.id", target = "orderId")
  @Mapping(source = "o.customerName", target = "customerName")
  @Mapping(target = "createdDate", ignore = true)
  public abstract OrderDetailsInfoResponse mapOrderToOrderDetailsInfoResponse(Order o);

  @Mapping(source = "m.id", target = "id")
  @Mapping(source = "m.name", target = "name")
  public abstract MenuDropdownDTO mapMenuToMenuDropdownDTO(Menu m);

  @Mapping(source = "odmor.menuName", target = "menuName")
  @Mapping(source = "odmor.price", target = "price")
  @Mapping(source = "odmor.priceString", target = "priceString")
  @Mapping(source = "odmor.quantity", target = "quantity")
  @Mapping(source = "odmor.notes", target = "notes")
  public abstract ReceiptMenuJsonDTO mapOrderDetailsMenuOrderedResponseToReceiptMenuJsonDTO(OrderDetailsMenuOrderedResponse odmor);

  @Mapping(source = "t.id", target = "transactionId")
  @Mapping(source = "t.customerName", target = "customerName")
  @Mapping(source = "t.createdDate", target = "transactionDate")
  @Mapping(source = "t.totalPrice", target = "totalPrice")
  @Mapping(target = "displayedTransactionDate", ignore = true)
  @Mapping(target = "totalPriceString", ignore = true)
  public abstract TransactionListingDTO mapTransactionToTransactionListingDTO(Transaction t);

  @Mapping(source = "m.id", target = "menuId")
  @Mapping(source = "m.createdDate", target = "createdDate")
  @Mapping(source = "m.lastModifiedDate", target = "lastModifiedDate")
  @Mapping(source = "m.name", target = "menuName")
  @Mapping(source = "m.price", target = "price")
  @Mapping(target = "displayedCreatedDate", ignore = true)
  @Mapping(target = "displayedLastModifiedDate", ignore = true)
  @Mapping(target = "priceString", ignore = true)
  public abstract MenuListingResponse mapMenuToMenuListingResponse(Menu m);

  @Mapping(source = "m.id", target = "id")
  @Mapping(source = "m.name", target = "name")
  @Mapping(source = "m.price", target = "price")
  public abstract MenuUpdateViewResponse mapMenuToMenuUpdateViewResponse(Menu m);

}
