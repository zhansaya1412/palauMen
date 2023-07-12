package kz.batyrbek.palaumen.palaumen.mapper;

import kz.batyrbek.palaumen.palaumen.dto.CategoryDto;
import kz.batyrbek.palaumen.palaumen.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
}
