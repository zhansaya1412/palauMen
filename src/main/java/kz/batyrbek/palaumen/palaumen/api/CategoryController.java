package kz.batyrbek.palaumen.palaumen.api;

import kz.batyrbek.palaumen.palaumen.dto.CategoryDto;
import kz.batyrbek.palaumen.palaumen.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping(value="{id}")
    public CategoryDto getCategory(@PathVariable(name = "id") Long id){
        return categoryService.getCategory(id);
    }

    @PostMapping
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @PutMapping
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(categoryDto);
    }

    @DeleteMapping(value = "{id}")
    public void deleteCategory(@PathVariable(name = "id") Long id){
        categoryService.deleteCategory(id);
    }

}


