package kz.batyrbek.palaumen.palaumen.dto;

import jakarta.persistence.*;
import kz.batyrbek.palaumen.palaumen.model.Employee;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CookDto {
    private Long id;
    private String rating;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId")
    private EmployeeDto employee;
}
