package kz.batyrbek.palaumen.palaumen.service;

import kz.batyrbek.palaumen.palaumen.dto.CategoryDto;
import kz.batyrbek.palaumen.palaumen.dto.FoodDto;
import kz.batyrbek.palaumen.palaumen.mapper.FoodMapper;
import kz.batyrbek.palaumen.palaumen.model.Category;
import kz.batyrbek.palaumen.palaumen.model.Food;
import kz.batyrbek.palaumen.palaumen.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    public List<FoodDto> getFoods(){
        List<Food> foods = foodRepository.findAll();
        List<FoodDto> foodDtoList = new ArrayList<>();
        foods.forEach(food -> foodDtoList.add(foodMapper.toDto(food)));
        return foodDtoList;
    }

   public List<FoodDto> loadFoods(String url){
        List<Food> foods = foodRepository.findAllByCategory_Url(url);
        List<FoodDto> foodDtoList = new ArrayList<>();
        foods.forEach(food -> foodDtoList.add(foodMapper.toDto(food)));
        return foodDtoList;
    }

    public boolean foodUrlExists(String foodUrl) {
        return foodRepository.findOptionalByFoodUrl(foodUrl).isPresent();
    }
    public FoodDto addFood(FoodDto foodDto){
        Food food = foodMapper.toEntity(foodDto);
        return foodMapper.toDto(foodRepository.save(food));
    }
    public FoodDto getFood(Long id){
        return foodMapper.toDto(foodRepository.findById(id).orElseThrow());
    }

    public FoodDto getFoodUrl(String foodUrl){

        return foodMapper.toDto(foodRepository.findByFoodUrl(foodUrl));
    }

    public FoodDto saveFood(FoodDto foodDto){
        return foodMapper.toDto(foodRepository.save(foodMapper.toEntity(foodDto)));
    }
    public FoodDto updateFood(FoodDto foodDto) {
        Food existingFood = foodRepository.findById(foodDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Food not found"));

        existingFood.setId(foodDto.getId());
        existingFood.setFoodUrl(foodDto.getFoodUrl());
        existingFood.setFoodName(foodDto.getFoodName());
        existingFood.setDescription(foodDto.getDescription());
        existingFood.setWeight(foodDto.getWeight());
        existingFood.setPricePerKg(foodDto.getPricePerKg());
        existingFood.setQuantity(foodDto.getQuantity());
        existingFood.setPricePerUnit(foodDto.getPricePerUnit());
        existingFood.setCategory( categoryDtoToCategory( foodDto.getCategory() ) );
        return foodMapper.toDto(foodRepository.save(existingFood));
    }
    protected Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }
        Category category = new Category();
        category.setId( categoryDto.getId() );
        category.setName( categoryDto.getName() );
        category.setUrl( categoryDto.getUrl() );
        category.setPriceFor( categoryDto.getPriceFor() );
        return category;
    }


    public void deleteFood(Long id){
        foodRepository.deleteById(id);
    }

    public List<FoodDto> searchFood(String search) {
        List<Food> foods = foodRepository.findByCategoryNameOrFoodName(search);
        List<FoodDto> foodDtos = new ArrayList<>();
        foods.forEach(food -> foodDtos.add(foodMapper.toDto(food)));
        return foodDtos;
    }
    public BigDecimal getPrice(String foodUrl, BigDecimal weight, Integer quantity) {
        Food food = foodRepository.findByFoodUrl(foodUrl);
        if (food == null) {
            throw new RuntimeException("Food not found");
        }
        BigDecimal pricePerUnit = food.getPricePerUnit();
        BigDecimal foodWeight = food.getWeight();
        int foodQuantity = food.getQuantity();

        if (weight != null && foodWeight.compareTo(BigDecimal.ZERO) != 0) {
            return (pricePerUnit.multiply(weight)).divide(foodWeight);
        } else if (quantity != null) {
            return pricePerUnit.multiply(BigDecimal.valueOf(quantity).divide(BigDecimal.valueOf(foodQuantity)));
        } else {
            return pricePerUnit;
        }
    }
}
