package com.ms.orderservice.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class MQConfiguration {

    public static final String ORDER_SERVICE_QUEUE = "order_service_queue";
    public static final String ORDER_SERVICE_EXCHANGE = "order_service_exchange";
    public static final String ORDER_SERVICE_ROUTING = "order_service_routing";
    public static final String DELIVERY_SERVICE_QUEUE = "delivery_service_queue";
    public static final String DELIVERY_SERVICE_EXCHANGE = "delivery_service_exchange";
    public static final String DELIVERY_SERVICE_ROUTING = "delivery_service_routing";
    public static final String DELIVERY_ID_SERVICE_QUEUE = "delivery_id_service_queue";
    public static final String DELIVERY_ID_SERVICE_EXCHANGE = "delivery_status_service_exchange";
    public static final String DELIVERY_ID_SERVICE_ROUTING = "delivery_status_service_routing";
    public static final String DELIVERY_STATUS_SERVICE_QUEUE = "delivery_status_service_queue";
    public static final String DELIVERY_STATUS_SERVICE_EXCHANGE = "delivery_status_service_exchange";
    public static final String DELIVERY_STATUS_SERVICE_ROUTING = "delivery_status_service_routing";


    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_SERVICE_QUEUE);
    }

    @Bean
    public Queue deliveryQueue() {
        return new Queue(DELIVERY_SERVICE_QUEUE);
    }

    @Bean
    public Queue deliveryIdQueue() {
        return new Queue(DELIVERY_ID_SERVICE_QUEUE);
    }

    /*@Bean
    public Queue deliveryStatusQueue() {
        return new Queue(DELIVERY_STATUS_SERVICE_QUEUE);
    }*/

    @Bean
    public Exchange orderExchange() {
        return ExchangeBuilder.directExchange(ORDER_SERVICE_EXCHANGE).build();
    }

    @Bean
    public Exchange deliveryExchange() {
        return ExchangeBuilder.directExchange(DELIVERY_SERVICE_EXCHANGE).build();
    }

    @Bean
    public Exchange deliveryIdExchange() {
        return ExchangeBuilder.directExchange(DELIVERY_ID_SERVICE_EXCHANGE).build();
    }

    /*@Bean
    public Exchange deliveryStatusExchange() {
        return ExchangeBuilder.directExchange(DELIVERY_STATUS_SERVICE_EXCHANGE).build();
    }*/

    @Bean
    Binding orderBinding() {
        return BindingBuilder
                .bind(orderQueue())
                .to(orderExchange())
                .with(ORDER_SERVICE_ROUTING)
                .noargs();
    }

    @Bean
    Binding deliveryBinding() {
        return BindingBuilder
                .bind(deliveryQueue())
                .to(deliveryExchange())
                .with(DELIVERY_SERVICE_ROUTING)
                .noargs();
    }

    @Bean
    Binding deliveryIdBinding() {
        return BindingBuilder
                .bind(deliveryIdQueue())
                .to(deliveryIdExchange())
                .with(DELIVERY_ID_SERVICE_ROUTING)
                .noargs();
    }

    /*@Bean
    Binding deliveryStatusBinding() {
        return BindingBuilder
                .bind(deliveryStatusQueue())
                .to(deliveryStatusExchange())
                .with(DELIVERY_STATUS_SERVICE_ROUTING)
                .noargs();
    }*/

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}

