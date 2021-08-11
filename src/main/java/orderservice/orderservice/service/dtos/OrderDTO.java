package orderservice.orderservice.service.dtos;

import orderservice.orderservice.domain.OrderLine;

import java.util.List;

public class OrderDTO {
    private String id;
    private List<OrderLineDTO> orderLines;
    private String status;
    private String timeStamp;

    public OrderDTO(String id, String status, String timeStamp) {
        this.id = id;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderLineDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineDTO> orderLines) {
        this.orderLines = orderLines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
