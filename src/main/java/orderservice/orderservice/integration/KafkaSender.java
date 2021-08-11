package orderservice.orderservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import orderservice.orderservice.domain.OrderPlacedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderPlaced(String topic, OrderPlacedEvent message){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String resultAsString = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, resultAsString);
        } catch(Exception ex) {}
    }
}
