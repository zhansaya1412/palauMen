package kz.batyrbek.palaumen.palaumen.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
public class Role extends BaseModel implements GrantedAuthority {
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
