package kz.batyrbek.palaumen.palaumen.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter

public class ExtraDetail extends BaseModel{
    private String name;
    private int price;
    private String description;
}
