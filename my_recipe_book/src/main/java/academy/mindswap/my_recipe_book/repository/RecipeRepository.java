package academy.mindswap.my_recipe_book.repository;

import academy.mindswap.my_recipe_book.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe , Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Recipe r where r.id = ?1")
    void deleteById(Long id);

   default void upDate(Recipe recipe){
       Recipe recipe1=findById(recipe.getId()).orElse(null);
       if (recipe == null){
           throw new NullPointerException("nao existe ");
       }
   }
}
