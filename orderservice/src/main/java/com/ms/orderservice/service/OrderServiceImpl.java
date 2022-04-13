package com.ms.orderservice.service;

import com.ms.orderservice.configuration.MQConfiguration;
import com.ms.orderservice.dto.Delivery;
import com.ms.orderservice.dto.Payment;
import com.ms.orderservice.entity.Order;
import com.ms.orderservice.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    public static final String PAYMENT_SERVICE = "http://PAYMENT-SERVICE";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Order save(Order order) {
        order.setTotalPrice(order.getUnitPrice()*order.getQuantity());
        order = orderRepository.save(order);
        Payment payment = new Payment();
        payment.setOrderId(order.getOrderId());
        payment.setProductId(order.getProductId());
        payment = restTemplate.postForObject(PAYMENT_SERVICE + "/payment/save", payment, Payment.class);
        if(payment!=null && payment.getPaymentStatus().equals("success")) {
            order.setPaymentId(payment.getPaymentId());
            order = orderRepository.save(order);
        }
        else {
            orderRepository.deleteById(order.getOrderId());
            order=null;
        }
        return order;
    }

    @Override
    public List<Order> allOrders() {
        return orderRepository.findAll();
    }

    @Override
    public String delete(long id) {
        orderRepository.deleteById(id);
        return "Order deleted";
    }

    @Override//To update the order status to placed based on order id
    public String updateOrderStatus(Delivery delivery) {
       int recordsUpdated = orderRepository.updateOrderStatus(delivery.getDeliveryStatus(),delivery.getOrderId());
        String response = recordsUpdated<0 ? "status not updated" : "status updated";
        if(response.equals("status updated") && delivery.getDeliveryId()==null){
            //Delivery delivery=new Delivery();
            //delivery.setOrderId(id);
            rabbitTemplate.convertAndSend(MQConfiguration.ORDER_SERVICE_EXCHANGE,MQConfiguration.ORDER_SERVICE_ROUTING,delivery);
        }
        return response;
    }

    @Override
    public Integer updateDeliveryId(Long orderId, Long deliveryId) {
        return orderRepository.updateDeliveryId(orderId,deliveryId);
    }
}
