package kz.batyrbek.palaumen.palaumen.mapper;

import kz.batyrbek.palaumen.palaumen.dto.FoodDto;
import kz.batyrbek.palaumen.palaumen.dto.UsersDto;
import kz.batyrbek.palaumen.palaumen.model.Food;
import kz.batyrbek.palaumen.palaumen.model.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDto toDto(Users users);
    Users toEntity(UsersDto usersDto);

}
