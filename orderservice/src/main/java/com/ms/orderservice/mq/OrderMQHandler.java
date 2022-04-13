package com.ms.orderservice.mq;


import com.ms.orderservice.configuration.MQConfiguration;
import com.ms.orderservice.dto.Delivery;
import com.ms.orderservice.entity.Order;
import com.ms.orderservice.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMQHandler {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = MQConfiguration.DELIVERY_SERVICE_QUEUE)
    public void onSuccessfulOrderPlacedHandler(Delivery delivery){
        String updateOrderStatus = orderService.updateOrderStatus(delivery);
        System.out.println(updateOrderStatus);
    }

    @RabbitListener(queues = MQConfiguration.DELIVERY_ID_SERVICE_QUEUE)
    public void updateDeliveryId(Delivery delivery){
        Integer deliveryId = orderService.updateDeliveryId(delivery.getOrderId(),delivery.getDeliveryId());
        System.out.println(deliveryId);
    }
}
