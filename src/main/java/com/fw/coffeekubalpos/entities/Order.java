package com.fw.coffeekubalpos.entities;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "orderr")
@Entity
@Getter
@Setter
public class Order {

  @Id
  @Column(name = "id")
  @GeneratedValue
  private UUID id;

  @Column(name = "created_date")
  @CreationTimestamp
  private ZonedDateTime createdDate;

  @Column(name = "last_modified_date")
  @UpdateTimestamp
  private ZonedDateTime lastModifiedDate;

  @Column(name = "customer_name")
  @NotNull
  @NotEmpty
  private String customerName;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  private Set<OrderMenu> orderMenu;

  public Order() {}

  public Order(UUID id) {
    this.id = id;
  }

}
