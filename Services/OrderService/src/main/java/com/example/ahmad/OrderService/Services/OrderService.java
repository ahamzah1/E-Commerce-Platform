package com.example.ahmad.OrderService.Services;

import com.example.ahmad.OrderService.DTO.OrderResponse;
import com.example.ahmad.OrderService.Entity.Order;
import com.example.ahmad.OrderService.Entity.OrderLine;
import com.example.ahmad.OrderService.Exceptions.BusinessException;
import com.example.ahmad.OrderService.Communication.CustomerClient;
import com.example.ahmad.OrderService.Communication.CustomerResponse;
import com.example.ahmad.OrderService.Communication.ProductClient;
import com.example.ahmad.OrderService.Communication.ProductPurchaseResponse;
import com.example.ahmad.OrderService.DTO.OrderRequest;
import com.example.ahmad.OrderService.Exceptions.OrderNotFoundException;
import com.example.ahmad.OrderService.Kafka.OrderConfirmation;
import com.example.ahmad.OrderService.Kafka.OrderProducer;
import com.example.ahmad.OrderService.Mappers.OrderMapper;
import com.example.ahmad.OrderService.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private  final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerClient customerClient, ProductClient productClient, OrderMapper orderMapper, OrderLineService orderLineService, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderMapper = orderMapper;
        this.orderLineService = orderLineService;
        this.orderProducer = orderProducer;
    }

    @Transactional
    public int handleOrder(OrderRequest order) {
        try{
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

            orderProducer.SendOrderConfirmation(
                    new OrderConfirmation(
                            order.getReference(),
                            order.getAmount(),
                            order.getPaymentMethod(),
                            customer,
                            res
                    )
            );

            return order_saved.getId();

        } catch (Exception e){
            this.productClient.revertPurchaseProducts(order.getProducts());
            throw e;
        }
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(OrderResponse::new).collect(Collectors.toList());
    }

    public OrderResponse findById(int id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found!"));
        return new OrderResponse(order);
    }
}
