package kz.batyrbek.palaumen.palaumen.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDto{
    private Long id;
    private String name;
    private String url;
    private String priceFor;
}
