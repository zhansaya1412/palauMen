package kz.batyrbek.palaumen.palaumen.repository;

import kz.batyrbek.palaumen.palaumen.model.Employee;
import kz.batyrbek.palaumen.palaumen.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByCategory_Url(String url);
    Food findByFoodUrl(String foodUrl);
    Optional<Food> findOptionalByFoodUrl(String foodUrl);
    @Query("SELECT f FROM Food f JOIN f.category c WHERE LOWER(c.name) LIKE %:search% OR LOWER(f.foodName) " +
            "LIKE %:search%")
    List<Food> findByCategoryNameOrFoodName(@Param("search") String search);
}
