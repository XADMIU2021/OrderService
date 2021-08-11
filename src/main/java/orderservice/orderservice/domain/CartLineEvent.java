package orderservice.orderservice.domain;

public class CartLineEvent {
    private String productNumber;
    private int quantity;

    public String getProductNumber() {
        return productNumber;
    }

    public int getQuantity() {
        return quantity;
    }
}
