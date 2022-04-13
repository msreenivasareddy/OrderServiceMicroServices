package com.ms.orderservice.controller;

import com.ms.orderservice.dto.Delivery;
import com.ms.orderservice.entity.Order;
import com.ms.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public Order save(@RequestBody Order order){
        return orderService.save(order);
    }

    @PostMapping("/update_order_status")
    public String updateOrderStatus(@RequestBody Delivery delivery){
        return orderService.updateOrderStatus(delivery);
    }

    @GetMapping("/all")
    public List<Order> fetchAllOrders(){
        return orderService.allOrders();
    }

    @DeleteMapping("/delete/{orderId}")
    public String delete(@PathVariable long orderId){
        return orderService.delete(orderId);
    }
}
