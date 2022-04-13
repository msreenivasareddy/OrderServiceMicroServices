package com.ms.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ORDER_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    @JsonProperty("order_id")
    private Long orderId;

    private byte quantity;

    @JsonProperty("unit_price")
    @Column(name = "unit_price")
    private float unitPrice;

    @JsonProperty("total_price")
    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "order_status")
    @JsonProperty("order_status")
    private String orderStatus;

    @CreationTimestamp
    @JsonProperty("order_date")
    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "product_id")
    @JsonProperty("product_id")
    private Long productId;

    @Column(name = "payment_id")
    @JsonProperty("payment_id")
    private Long paymentId;

    @Column(name = "delivery_id")
    @JsonProperty("delivery_id")
    private Long deliveryId;

}
