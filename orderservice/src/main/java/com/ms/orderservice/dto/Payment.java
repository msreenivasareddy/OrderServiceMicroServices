package com.ms.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @JsonProperty("payment_id")
    private long paymentId;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("payment_date")
    private Timestamp paymentDate;

    @JsonProperty("product_id")
    private long productId;

    @JsonProperty("order_id")
    private long orderId;

}
