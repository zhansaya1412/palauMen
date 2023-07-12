package kz.batyrbek.palaumen.palaumen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="category")
public class Category extends BaseModel{
    private String name;
    private String url;
    private String priceFor;;
}
