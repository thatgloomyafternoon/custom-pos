package com.fw.coffeekubalpos;

import com.fw.coffeekubalpos.configurations.SpringBootStartup;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeKubalPos {

  public static void main(String[] args) {
    SpringBootStartup.run(CoffeeKubalPos.class, args);
  }

}
