package academy.mindswap.my_recipe_book.repository;

import academy.mindswap.my_recipe_book.model.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Modifying
    @Query("DELETE FROM Ingredient r where r.id = ?1")
    void deleteById(Long id);
}
