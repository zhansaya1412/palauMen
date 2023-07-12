package kz.batyrbek.palaumen.palaumen.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import kz.batyrbek.palaumen.palaumen.model.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class AddressDto {
    private Long id;
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
}
