package orderservice.orderservice.domain;

public class OrderLine {
    private String productNumber;
    private int quantity;

    public OrderLine(String productNumber, int quantity) {
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
