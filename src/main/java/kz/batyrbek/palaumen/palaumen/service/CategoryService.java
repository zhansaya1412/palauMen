package kz.batyrbek.palaumen.palaumen.service;

import kz.batyrbek.palaumen.palaumen.dto.CategoryDto;
import kz.batyrbek.palaumen.palaumen.mapper.CategoryMapper;
import kz.batyrbek.palaumen.palaumen.model.Category;
import kz.batyrbek.palaumen.palaumen.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categories.forEach(category -> categoryDtoList.add(categoryMapper.toDto(category)));
        return categoryDtoList;
    }

    public CategoryDto addCategory(CategoryDto categoryDto){
        Category category = categoryMapper.toEntity(categoryDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public CategoryDto getCategory(Long id){
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow());
    }

    public CategoryDto updateCategory(CategoryDto categoryDto){
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryDto)));
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
