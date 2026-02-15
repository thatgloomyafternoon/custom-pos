package com.fw.coffeekubalpos.services.impl;

import com.fw.coffeekubalpos.constants.PropertyConstants;
import com.fw.coffeekubalpos.entities.Menu;
import com.fw.coffeekubalpos.repositories.MenuRepository;
import com.fw.coffeekubalpos.services.MenuService;
import com.fw.coffeekubalpos.services.dto.MenuDropdownDTO;
import com.fw.coffeekubalpos.utils.CurrencyStringFormatUtil;
import com.fw.coffeekubalpos.utils.MapperUtil;
import com.fw.coffeekubalpos.web.responses.MenuCreateResponse;
import com.fw.coffeekubalpos.web.responses.MenuDeleteResponse;
import com.fw.coffeekubalpos.web.responses.MenuListingResponse;
import com.fw.coffeekubalpos.web.responses.MenuUpdateResponse;
import com.fw.coffeekubalpos.web.responses.MenuUpdateViewResponse;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

  private final MapperUtil mapperUtil;
  private final CurrencyStringFormatUtil currencyStringFormatUtil;
  private final MenuRepository menuRepository;

  public MenuServiceImpl(MapperUtil mapperUtil,
                         CurrencyStringFormatUtil currencyStringFormatUtil,
                         MenuRepository menuRepository) {
    this.mapperUtil = mapperUtil;
    this.currencyStringFormatUtil = currencyStringFormatUtil;
    this.menuRepository = menuRepository;
  }

  @Override
  public List<MenuDropdownDTO> getMenuDropdowns() {
    return menuRepository.findAll()
      .stream()
      .map(mapperUtil::mapMenuToMenuDropdownDTO)
      .sorted(Comparator.comparing(MenuDropdownDTO::getName, String.CASE_INSENSITIVE_ORDER))
      .collect(Collectors.toList());
  }

  @Override
  public List<MenuListingResponse> getAllMenus() {
    List<MenuListingResponse> menuListingResponses = menuRepository.findAll()
      .stream()
      .map(mapperUtil::mapMenuToMenuListingResponse)
      .sorted(Comparator.comparing(MenuListingResponse::getMenuName, String.CASE_INSENSITIVE_ORDER))
      .collect(Collectors.toList());
    menuListingResponses.forEach(mlr -> mlr.setPriceString(currencyStringFormatUtil.format(mlr.getPrice())));
    /** convert time zone */
    menuListingResponses.forEach(m -> m.setDisplayedCreatedDate(m.getCreatedDate().plusHours(7).format(DateTimeFormatter.ofPattern(PropertyConstants.DATE_TIME_PATTERN))));
    menuListingResponses.forEach(m -> m.setDisplayedLastModifiedDate(m.getLastModifiedDate().plusHours(7).format(DateTimeFormatter.ofPattern(PropertyConstants.DATE_TIME_PATTERN))));
    /** */
    return menuListingResponses;
  }

  @Override
  public MenuUpdateViewResponse getMenuByMenuId(UUID menuId) {
    Optional<Menu> optMenu = menuRepository.findById(menuId);
    if(!optMenu.isPresent()) {
      throw new IllegalArgumentException("Menu not found");
    }
    MenuUpdateViewResponse response = mapperUtil.mapMenuToMenuUpdateViewResponse(optMenu.get());
    return response;
  }

  @Override
  public MenuCreateResponse createMenu(String name, Integer price) {
    Menu newMenu = new Menu();
    newMenu.setName(name);
    newMenu.setPrice(price);
    menuRepository.save(newMenu);
    MenuCreateResponse response = new MenuCreateResponse();
    response.setMessage("ADD MENU SUCCESS");
    return response;
  }

  @Override
  public MenuUpdateResponse updateMenu(UUID id, String name, Integer price) {
    Optional<Menu> optMenu = menuRepository.findById(id);
    if(!optMenu.isPresent()) {
      throw new IllegalArgumentException("Menu not found");
    }
    Menu menu = optMenu.get();
    menu.setName(name);
    menu.setPrice(price);
    menuRepository.save(menu);
    MenuUpdateResponse response = new MenuUpdateResponse();
    response.setMessage("UPDATE MENU SUCCESS");
    return response;
  }

  @Override
  public MenuDeleteResponse deleteMenu(UUID id) {
    Optional<Menu> optMenu = menuRepository.findById(id);
    if(!optMenu.isPresent()) {
      throw new IllegalArgumentException("Menu not found");
    }
    menuRepository.deleteById(id);
    MenuDeleteResponse response = new MenuDeleteResponse();
    response.setMessage("DELETE MENU SUCCESS");
    return response;
  }

}
