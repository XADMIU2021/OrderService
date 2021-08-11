package orderservice.orderservice.service.dtos;

import java.util.List;

public class OrdersDTO {
    private List<OrderDTO> orders;

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
