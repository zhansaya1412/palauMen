package kz.batyrbek.palaumen.palaumen.model;

import jakarta.persistence.*;
import kz.batyrbek.palaumen.palaumen.dto.UsersDto;
import lombok.*;

import java.lang.reflect.Type;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseModel{
    private String position;
    private int salary;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;
    public enum EmploymentStatus {
        ACTIVE,
        INACTIVE
    }

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @ManyToOne
    @JoinColumn(name = "users_id", unique = true)
    private Users users;

}
