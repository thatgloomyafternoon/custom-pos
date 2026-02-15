package com.fw.coffeekubalpos.web.requests;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuUpdateRequest {

  @NotNull
  private String id;
  @NotNull
  private String name;
  @NotNull
  private Integer price;

}
