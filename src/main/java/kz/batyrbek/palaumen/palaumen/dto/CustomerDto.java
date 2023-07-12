package kz.batyrbek.palaumen.palaumen.dto;

import jakarta.persistence.*;
import kz.batyrbek.palaumen.palaumen.model.Address;
import kz.batyrbek.palaumen.palaumen.model.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder

public class CustomerDto {
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "users_id", unique = true)
    private UsersDto users;
}
