package com.ms.orderservice.repository;

import com.ms.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Modifying
    @Query(value = "update ORDER_TB ot set ot.order_status = :status where ot.order_id = :id",nativeQuery = true)
    @Transactional
    Integer updateOrderStatus(@Param(value = "status") String status,@Param(value="id") Long id);

    @Modifying
    @Query(value = "update ORDER_TB ot set ot.delivery_id = :deliveryId where ot.order_id = :orderId",nativeQuery = true)
    @Transactional
    Integer updateDeliveryId(@Param(value = "orderId") Long orderId,@Param(value = "deliveryId") Long deliveryId);

    @Modifying
    @Query(value = "update ORDER_TB ot set ot.order_status = :status where ot.order_id = :id",nativeQuery = true)
    @Transactional
    Integer updateDeliveryStatus(@Param(value = "status") String status,@Param(value="id") Long id);
}
