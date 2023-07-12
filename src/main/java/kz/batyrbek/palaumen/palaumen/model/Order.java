package kz.batyrbek.palaumen.palaumen.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "my_orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cook cook;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryMan deliveryMan;

    private LocalDateTime orderTime;

    private BigDecimal totalPrice;

    public enum OrderStatus {
        NEW,
        IN_PROGRESS,
        DELIVERED,
        CANCELLED
    }

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "commentary", columnDefinition="TEXT")
    private String commentary;
}

