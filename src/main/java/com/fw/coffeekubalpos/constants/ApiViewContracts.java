package com.fw.coffeekubalpos.constants;

@SuppressWarnings("ALL")
public interface ApiViewContracts {

  /**
   * /auth/login
   * /menu/list
   * /menu/create
   * /menu/update/{id}
   * /order/list
   * /order/details/{id}
   * /order/create
   * /order-menu/create/{id}
   * /transaction/list
   * /transaction/filter
   * /transaction/view-receipt/{id}
   * /api/menu/dropdown
   * /api/menu/create
   * /api/menu/update
   * /api/menu/delete
   * /api/order/create
   * /api/order/create-details
   * /api/order/delete
   * /api/order-menu/create
   * /api/order-menu/delete
   * /api/transaction/submit-payment
   */

  /** base path */
  String API = "/api";

  /** level-1 path */
  String AUTH = "/auth";
  String ORDER = "/order";
  String MENU = "/menu";
  String ORDER_MENU = "/order-menu";
  String TRANSACTION = "/transaction";

  /** level-2 path */
  String LOGIN = "/login";
  String LIST = "/list";
  String CREATE = "/create";
  String CREATE_DETAILS = "/create-details";
  String UPDATE = "/update";
  String DETAILS = "/details";
  String DELETE = "/delete";
  String SUBMIT_PAYMENT = "/submit-payment";
  String VIEW_RECEIPT = "/view-receipt";
  String DROPDOWN = "/dropdown";
  String FILTER = "/filter";

}
