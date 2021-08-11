package orderservice.orderservice.domain;

import java.util.List;

public class CreateOrderEvent {
    private String customerId;
    private String cartNumber;
    private List<CartLineEvent> cartLines;

    public CreateOrderEvent() {
    }

    public CreateOrderEvent(String customerId, String cartNumber, List<CartLineEvent> cartLines) {
        this.customerId = customerId;
        this.cartNumber = cartNumber;
        this.cartLines = cartLines;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public List<CartLineEvent> getCartLines() {
        return cartLines;
    }
}
