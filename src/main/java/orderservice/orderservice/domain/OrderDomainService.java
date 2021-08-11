package orderservice.orderservice.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDomainService {
    public Order createOrderFromEvent(CreateOrderEvent event) {
        Order order = new Order(event.getCustomerId());
        List<OrderLine> orderLines = new ArrayList<OrderLine>();
        for(CartLineEvent line: event.getCartLines()) {
            orderLines.add(this.getOrderLineFromEvent(line));
        }
        order.setOrderLines(orderLines);
        return order;
    }

    private OrderLine getOrderLineFromEvent(CartLineEvent event) {
        return new OrderLine(event.getProductNumber(), event.getQuantity());
    }

    public OrderPlacedEvent getOrderPlacedEventFromOrder(Order order) {
        List<OrderLineEvent> orderLineEvents = new ArrayList<OrderLineEvent>();
        for(OrderLine line: order.getOrderLines()) {
            orderLineEvents.add(this.getOrderLineEventFromOrderLine(line));
        }
        OrderPlacedEvent event = new OrderPlacedEvent(orderLineEvents, order.getCustomerId());
        return event;
    }

    private OrderLineEvent getOrderLineEventFromOrderLine(OrderLine line) {
        return new OrderLineEvent(line.getProductNumber(), line.getQuantity());
    }
}
