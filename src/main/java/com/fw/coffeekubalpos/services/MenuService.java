package com.fw.coffeekubalpos.services;

import com.fw.coffeekubalpos.services.dto.MenuDropdownDTO;
import com.fw.coffeekubalpos.web.responses.MenuCreateResponse;
import com.fw.coffeekubalpos.web.responses.MenuDeleteResponse;
import com.fw.coffeekubalpos.web.responses.MenuListingResponse;
import com.fw.coffeekubalpos.web.responses.MenuUpdateResponse;
import com.fw.coffeekubalpos.web.responses.MenuUpdateViewResponse;
import java.util.List;
import java.util.UUID;

public interface MenuService {

  List<MenuDropdownDTO> getMenuDropdowns();
  List<MenuListingResponse> getAllMenus();
  MenuUpdateViewResponse getMenuByMenuId(UUID menuId);
  MenuCreateResponse createMenu(String name, Integer price);
  MenuUpdateResponse updateMenu(UUID id, String name, Integer price);
  MenuDeleteResponse deleteMenu(UUID id);

}
