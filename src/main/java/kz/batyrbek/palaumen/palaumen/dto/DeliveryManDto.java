package kz.batyrbek.palaumen.palaumen.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder

public class DeliveryManDto {
    private Long id;
    private String numberOfCar;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId")
    private EmployeeDto employee;
}
