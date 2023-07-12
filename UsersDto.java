package kz.batyrbek.palaumen.palaumen.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UsersDto {
    private Long id;
    private String email;
    private String password;
    private String fullname;
    private String phone;
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleDto> roles;

}
