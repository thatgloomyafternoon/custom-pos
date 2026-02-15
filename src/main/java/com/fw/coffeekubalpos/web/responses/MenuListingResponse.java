package com.fw.coffeekubalpos.web.responses;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuListingResponse {

  private UUID menuId;
  private String menuName;
  private ZonedDateTime createdDate;
  private String displayedCreatedDate;
  private ZonedDateTime lastModifiedDate;
  private String displayedLastModifiedDate;
  private Integer price;
  private String priceString;

}
