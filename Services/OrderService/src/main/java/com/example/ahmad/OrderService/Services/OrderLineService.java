package com.example.ahmad.OrderService.Services;

import com.example.ahmad.OrderService.Communication.ProductPurchaseResponse;
import com.example.ahmad.OrderService.Entity.Order;
import com.example.ahmad.OrderService.Entity.OrderLine;
import com.example.ahmad.OrderService.Repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;

    public OrderLine saveOrderLine(ProductPurchaseResponse input , Order order){
        return this.orderLineRepository.save(new OrderLine(input,order));
    }
}
