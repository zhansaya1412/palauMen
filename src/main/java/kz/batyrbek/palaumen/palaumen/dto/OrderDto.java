package kz.batyrbek.palaumen.palaumen.dto;

import jakarta.persistence.*;
import kz.batyrbek.palaumen.palaumen.model.Order;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder

public class OrderDto {
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerDto customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<CartItemDto> items;

    @ManyToOne(fetch = FetchType.LAZY)
    private CookDto cook;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryManDto deliveryMan;

    private LocalDateTime orderTime;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private Order.OrderStatus status;

    private String commentary;
}

