package com.matimbe.ecommerce.orderline;


import com.matimbe.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity

public class OrderLine {
    @Id
    @Generated
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer quantity;
    private double productId;
}
