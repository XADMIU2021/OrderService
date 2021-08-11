package orderservice.orderservice.service;

import orderservice.orderservice.domain.Order;
import orderservice.orderservice.domain.OrderLine;
import orderservice.orderservice.service.dtos.OrderDTO;
import orderservice.orderservice.service.dtos.OrderLineDTO;
import orderservice.orderservice.service.dtos.OrdersDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderAdapterService {
    public OrderDTO getDTOFromOrder(Order order) {
        OrderDTO dto = new OrderDTO(order.getId(), order.getStatus(), order.getTimeStamp());
        List<OrderLineDTO> orderLineDTOs = new ArrayList<OrderLineDTO>();
        for (OrderLine line: order.getOrderLines()) {
            orderLineDTOs.add(this.getOrderLineDTOFromOrderLine(line));
        }
        dto.setOrderLines(orderLineDTOs);

        return dto;
    }

    public Order getOrderFromDTO(OrderDTO dto, String customerId) {
        Order order = new Order(customerId);
        List<OrderLine> orderLines = new ArrayList<OrderLine>();
        for (OrderLineDTO lineDTO: dto.getOrderLines()) {
            orderLines.add(this.getOrderLineFromDTO(lineDTO));
        }

        order.setOrderLines(orderLines);
        return order;
    }

    public OrdersDTO getDTOSFromOrders(List<Order> orders) {
        OrdersDTO dtos = new OrdersDTO();
        List<OrderDTO> orderDTOS = new ArrayList<OrderDTO>();
        for (Order order: orders) {
            orderDTOS.add(this.getDTOFromOrder(order));
        }

        dtos.setOrders(orderDTOS);
        return dtos;
    }

    private OrderLineDTO getOrderLineDTOFromOrderLine(OrderLine line) {
        return new OrderLineDTO(line.getProductNumber(), line.getQuantity());
    }

    private OrderLine getOrderLineFromDTO(OrderLineDTO lineDTO) {
        return new OrderLine(lineDTO.getProductNumber(), lineDTO.getQuantity());
    }
}
