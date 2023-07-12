package kz.batyrbek.palaumen.palaumen.mapper;

import kz.batyrbek.palaumen.palaumen.dto.RoleDto;
import kz.batyrbek.palaumen.palaumen.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toEntity(RoleDto roleDto);
    RoleDto toDto(Role role);
}
