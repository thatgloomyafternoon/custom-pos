package com.fw.coffeekubalpos.utils;

import org.springframework.stereotype.Component;

@Component
public class PathResolverUtil {

  public String resolve(String path) {
    return path.substring(1);
  }

}
