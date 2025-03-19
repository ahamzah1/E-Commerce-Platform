package com.example.ahmad.OrderService.Services;

import com.example.ahmad.OrderService.Entity.OrderLine;
import com.example.ahmad.OrderService.Exceptions.BusinessException;
import com.example.ahmad.OrderService.Communication.CustomerClient;
import com.example.ahmad.OrderService.Communication.CustomerResponse;
import com.example.ahmad.OrderService.Communication.ProductClient;
import com.example.ahmad.OrderService.Communication.ProductPurchaseResponse;
import com.example.ahmad.OrderService.DTO.OrderRequest;
import com.example.ahmad.OrderService.Mappers.OrderMapper;
import com.example.ahmad.OrderService.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CustomerClient customerClient;
    private ProductClient productClient;
    private final OrderMapper orderMapper;
    private  final OrderLineService orderLineService;


    @Autowired
    public OrderService(OrderRepository orderRepository, ProductClient productClient, CustomerClient customerClient, OrderMapper orderMapper, OrderLineService orderLineService) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.customerClient = customerClient;
        this.orderMapper = orderMapper;
        this.orderLineService = orderLineService;
    }
    
    public int handleOrder(OrderRequest order) {
        CustomerResponse customer = this.customerClient.findCustomerById(order.getCustomerId())
                .orElseThrow(() -> new BusinessException("Customer not found"));

        List<ProductPurchaseResponse> res= this.productClient.purchaseProducts(order.getProducts());
        var order_saved = this.orderRepository.save(this.orderMapper.toOrder(order));

        List<OrderLine> orderLines = new ArrayList<>();
        for(ProductPurchaseResponse x : res){
              orderLines.add(this.orderLineService.saveOrderLine(x, order_saved));
        }

        order_saved.setOrderLines(orderLines);
        this.orderRepository.save(order_saved);
        return order_saved.getId();
    }

}
