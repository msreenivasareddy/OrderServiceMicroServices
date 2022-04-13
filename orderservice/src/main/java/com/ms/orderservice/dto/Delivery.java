package com.ms.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    @JsonProperty("delivery_id")
    private Long deliveryId;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("delivery_status")
    private String deliveryStatus;

    @JsonProperty("delivery_location")
    private String deliveryLocation;
}
