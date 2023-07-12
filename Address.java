package kz.batyrbek.palaumen.palaumen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address extends BaseModel {
    private String address;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
}
