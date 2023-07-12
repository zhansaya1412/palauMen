package kz.batyrbek.palaumen.palaumen.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "users_id", unique = true)
    private Users users;

}
