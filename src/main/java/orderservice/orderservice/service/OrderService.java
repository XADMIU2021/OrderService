package orderservice.orderservice.service;

import orderservice.orderservice.data.OrderRepository;
import orderservice.orderservice.domain.CreateOrderEvent;
import orderservice.orderservice.domain.Order;
import orderservice.orderservice.domain.OrderDomainService;
import orderservice.orderservice.domain.OrderPlacedEvent;
import orderservice.orderservice.integration.KafkaSender;
import orderservice.orderservice.service.dtos.OrderDTO;
import orderservice.orderservice.service.dtos.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderAdapterService adapter;

    @Autowired
    private OrderDomainService domainService;

    @Autowired
    private KafkaSender sender;

    public void createOrderFromEvent(CreateOrderEvent event) {
        Order order = domainService.createOrderFromEvent(event);
        repository.save(order);
    }

    public OrderDTO CreateOrder(OrderDTO dto, String customerId) {
        Order order = adapter.getOrderFromDTO(dto, customerId);
        order = repository.save(order);

        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setTimeStamp(order.getTimeStamp());

        return dto;
    }

    public OrderDTO findById(String id) {
        Order order = repository.findById(id).orElse(null);
        return adapter.getDTOFromOrder(order);
    }

    public OrdersDTO findAll(String customerId) {
        List<Order> orders = repository.findByCustomerId(customerId);
        return adapter.getDTOSFromOrders(orders);
    }

    public void placeOrder(String orderId) {
        Order order = repository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus("PLACED");
            repository.save(order);

            // send kafka message
            OrderPlacedEvent event = domainService.getOrderPlacedEventFromOrder(order);
            sender.sendOrderPlaced("order-placed", event);
        }
    }
}
