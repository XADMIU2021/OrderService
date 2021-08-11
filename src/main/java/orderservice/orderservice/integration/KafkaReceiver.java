package orderservice.orderservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import orderservice.orderservice.domain.CreateOrderEvent;
import orderservice.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

public class KafkaReceiver {
    @Autowired
    private OrderService service;

    @KafkaListener(topics = {"create-order"})
    public void receiveAdd(@Payload String message) {
        System.out.println("Receiver received create order = " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CreateOrderEvent event = objectMapper.readValue(message, CreateOrderEvent.class);
            service.createOrderFromEvent(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
