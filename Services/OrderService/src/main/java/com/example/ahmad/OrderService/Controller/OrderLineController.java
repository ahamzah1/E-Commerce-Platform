package com.example.ahmad.OrderService.Controller;

import com.example.ahmad.OrderService.DTO.OrderLineResponse;
import com.example.ahmad.OrderService.Entity.OrderLine;
import com.example.ahmad.OrderService.Services.OrderLineService;
import jakarta.ws.rs.Path;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-lines")

public class OrderLineController {

    private OrderLineService orderLineService;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findAll(@PathVariable("order-id") int orderId){
        return ResponseEntity.ok(orderLineService.findByOrderId(orderId));
    }

}
