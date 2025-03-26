package com.example.ahmad.OrderService.Controller;

import com.example.ahmad.OrderService.DTO.OrderRequest;
import com.example.ahmad.OrderService.DTO.OrderResponse;
import com.example.ahmad.OrderService.Services.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

   private final OrderService orderService;

   @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Integer> createOrder(@Valid @RequestBody OrderRequest order){
        return ResponseEntity.ok(orderService.handleOrder(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") int id){
        return ResponseEntity.ok(orderService.findById(id));
    }

}
