package com.ms.orderservice.service;

import com.ms.orderservice.dto.Delivery;
import com.ms.orderservice.entity.Order;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderService {
    Order save(Order order);
    List<Order> allOrders();
    String delete(long id);
    String updateOrderStatus(Delivery delivery);

    Integer updateDeliveryId(Long orderId, Long deliveryId);
}
