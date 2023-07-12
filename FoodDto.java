package kz.batyrbek.palaumen.palaumen.dto;

import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Builder

public class FoodDto {
    private Long id;
    private String foodName;
    private String description = "";
    private BigDecimal weight;
    private BigDecimal pricePerKg;
    private Integer quantity;
    private BigDecimal pricePerUnit;
    private String photo;
    private String foodUrl;

    @ManyToOne
    private CategoryDto category;
}
