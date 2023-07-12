package kz.batyrbek.palaumen.palaumen.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ExtraDetailDto {
    private Long id;
    private String name;
    private int price;
    private String description;
}
