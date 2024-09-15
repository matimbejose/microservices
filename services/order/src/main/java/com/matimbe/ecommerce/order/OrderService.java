package com.matimbe.ecommerce.order;


import com.matimbe.ecommerce.customer.CustomerClient;
import com.matimbe.ecommerce.exception.BusinessException;
import com.matimbe.ecommerce.kafka.OrderConfirmation;
import com.matimbe.ecommerce.kafka.OrderProducer;
import com.matimbe.ecommerce.orderline.OrderLineRequest;
import com.matimbe.ecommerce.orderline.OrderLineService;
import com.matimbe.ecommerce.payment.PaymentClient;
import com.matimbe.ecommerce.payment.PaymentRequest;
import com.matimbe.ecommerce.product.ProductClient;
import com.matimbe.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {



    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        //check customer
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() ->new BusinessException("Cannot create order:: No customer found"));



        //purchase the products --> product-ms
       var  purchasedProducts =  this.productClient.purchaseProducts(request.products());

        var order = this.repository.save(mapper.toOrder(request));

        //persist  order
        for(PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

        //persist order list public

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        //todo start payment process
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.references(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        //send the order confirmation   -->notification-ms (kafka)
        return order.getId();
    }

    public List<OrderResponse> findAll() {

        return  repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return   repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() ->new EntityNotFoundException(String.format("Cannot find order:: No order found")));
    }
}
