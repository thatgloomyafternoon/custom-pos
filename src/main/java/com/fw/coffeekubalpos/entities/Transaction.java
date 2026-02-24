package com.fw.coffeekubalpos.entities;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.Setter;

@Table(name = "transaction")
@Entity
@Getter
@Setter
public class Transaction {

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

  @Column(name = "total_price")
  @NotNull
  private Integer totalPrice;

  @Column(name = "receipt_content_json", length = 5000)
  @NotNull
  @NotEmpty
  private String receiptContentJson;

}
