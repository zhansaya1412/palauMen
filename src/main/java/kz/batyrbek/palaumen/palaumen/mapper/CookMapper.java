package kz.batyrbek.palaumen.palaumen.mapper;

import kz.batyrbek.palaumen.palaumen.dto.CookDto;
import kz.batyrbek.palaumen.palaumen.model.Cook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CookMapper {
    CookDto toDto(Cook cook);
    Cook toEntity(CookDto cookDto);
}
