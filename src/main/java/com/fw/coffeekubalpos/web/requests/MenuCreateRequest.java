package com.fw.coffeekubalpos.web.requests;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuCreateRequest {

  @NotNull
  private String name;
  @NotNull
  private Integer price;

}
