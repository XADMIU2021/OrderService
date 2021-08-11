package orderservice.orderservice.controller;

import orderservice.orderservice.domain.Order;
import orderservice.orderservice.service.OrderService;
import orderservice.orderservice.service.dtos.OrderDTO;
import orderservice.orderservice.service.dtos.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO dto, @RequestHeader(value="Customer-ID") String customerId) {
        OrderDTO result = orderService.CreateOrder(dto, customerId);
        return new ResponseEntity<OrderDTO>(result, HttpStatus.CREATED);
    }

    @GetMapping("/order")
    public ResponseEntity<OrdersDTO> getAllOrders(@RequestHeader(value="Customer-ID") String customerId) {
        OrdersDTO dto = orderService.findAll(customerId);
        return new ResponseEntity<OrdersDTO>(dto, HttpStatus.OK);
    }

    @PostMapping("/order/{id}/place")
    public ResponseEntity<String> placeOrder(@RequestHeader(value="Customer-ID") String customerId, @PathVariable String id) {
        OrderDTO dto  = orderService.findById(id);
        if (dto == null) return new ResponseEntity<String>("Order not found", HttpStatus.NOT_FOUND);

        orderService.placeOrder(id);

        return new ResponseEntity<String>("Order place succeffully", HttpStatus.OK);
    }
}
