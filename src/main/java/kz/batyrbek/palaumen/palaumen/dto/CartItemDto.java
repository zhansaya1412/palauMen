package kz.batyrbek.palaumen.palaumen.dto;

import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Builder

public class CartItemDto {
    private Long id;
    private String foodName;
    private double price;
    private int quantity;
    private BigDecimal weight;

    @ManyToOne
    private FoodDto food;

    @ManyToOne
    private OrderDto order;
}

