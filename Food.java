package kz.batyrbek.palaumen.palaumen.model;

import jakarta.persistence.*;
import kz.batyrbek.palaumen.palaumen.dto.CategoryDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name="food")
@Getter
@Setter

public class Food extends BaseModel{
    private String foodName;

    @Column(name = "description", columnDefinition="TEXT")
    private String description = "";

    private BigDecimal weight;

    private BigDecimal pricePerKg;

    private Integer quantity;

    private BigDecimal pricePerUnit;

    private String photo;

    @Column(unique = true)
    private String foodUrl;

    @ManyToOne
    private Category category;


}
