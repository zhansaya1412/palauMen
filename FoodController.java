package kz.batyrbek.palaumen.palaumen.api;

import kz.batyrbek.palaumen.palaumen.dto.FoodDto;
import kz.batyrbek.palaumen.palaumen.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping
    public List<FoodDto> getFoods(){
        return foodService.getFoods();
    }

    @GetMapping(value="{id}")
    public FoodDto getFood(@PathVariable(name = "id") Long id){
        return foodService.getFood(id);
    }

    @GetMapping(value="/catalogue/{foodUrl}")
    public FoodDto getFoodUrl(@PathVariable(name = "foodUrl")  String foodUrl){
        return foodService. getFoodUrl(foodUrl);
    }

    @PostMapping
    public ResponseEntity<FoodDto> addFood(@RequestBody FoodDto foodDto) {
        if (foodService.foodUrlExists(foodDto.getFoodUrl())) {
            return ResponseEntity.badRequest().build();
        }
        FoodDto savedFoodDto = foodService.addFood(foodDto);
        return ResponseEntity.ok(savedFoodDto);
    }

    @PutMapping
    public FoodDto updateFood(@RequestBody FoodDto foodDto){
        return foodService.updateFood(foodDto);
    }

    @DeleteMapping(value = "{id}")
    public void deleteFood(@PathVariable(name = "id") Long id){
        foodService.deleteFood(id);
    }

    @GetMapping(value = "/category/{url}")
    public List<FoodDto> getFoodsByCategory(@PathVariable(name = "url") String url){
        return foodService.loadFoods(url);
    }

    @GetMapping("/search")
    public List<FoodDto> searchFood(@RequestParam(name = "q")  String query){
        return foodService.searchFood(query);
    }

    @PostMapping(value="/catalogue/{foodUrl}/price")
    public BigDecimal getPrice(@PathVariable(name = "foodUrl") String foodUrl,
                               @RequestParam(name = "weight", required = false) BigDecimal weight,
                               @RequestParam(name = "quantity", required = false) Integer quantity) {
        if (weight != null && quantity != null) {
            throw new IllegalArgumentException("Only one parameter is allowed: weight or quantity");
        }
        return foodService.getPrice(foodUrl, weight, quantity);
    }
}

