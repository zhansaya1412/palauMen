package kz.batyrbek.palaumen.palaumen.mapper;

import kz.batyrbek.palaumen.palaumen.dto.ExtraDetailDto;
import kz.batyrbek.palaumen.palaumen.model.ExtraDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExtraDetailMapper {
    ExtraDetailDto toDto(ExtraDetail extraDetail);
    ExtraDetail toEntity(ExtraDetailDto extraDetailDto);
}