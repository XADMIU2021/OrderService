package orderservice.orderservice.domain;

public class OrderLineEvent {
    private String productNumber;
    private int quantity;

    public OrderLineEvent(String productNumber, int quantity) {
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public int getQuantity() {
        return quantity;
    }
}
