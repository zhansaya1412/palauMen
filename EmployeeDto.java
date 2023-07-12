package kz.batyrbek.palaumen.palaumen.dto;

import jakarta.persistence.*;
import kz.batyrbek.palaumen.palaumen.model.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder

public class EmployeeDto {
    private Long id;
    private String position;
    private int salary;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;
    @Enumerated(EnumType.STRING)
    private Employee.EmploymentStatus employmentStatus;

    @ManyToOne
    private UsersDto users;

}
