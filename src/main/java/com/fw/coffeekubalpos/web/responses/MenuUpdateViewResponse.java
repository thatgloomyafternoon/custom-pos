package com.fw.coffeekubalpos.web.responses;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuUpdateViewResponse {

  private UUID id;
  private String name;
  private Integer price;

}
