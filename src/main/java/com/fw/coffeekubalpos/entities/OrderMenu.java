package com.fw.coffeekubalpos.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Table(name = "order_menu")
@Entity
@Getter
@Setter
public class OrderMenu {

  @Id
  @Column(name = "id")
  @GeneratedValue
  private UUID id;

  @JoinColumn(name = "order_id")
  @ManyToOne
  private Order order;

  @JoinColumn(name = "menu_id")
  @ManyToOne
  private Menu menu;

  @Column(name = "quantity")
  @Min(value = 1)
  @NotNull
  private Integer quantity;

  @Column(name = "notes")
  private String notes;

}
