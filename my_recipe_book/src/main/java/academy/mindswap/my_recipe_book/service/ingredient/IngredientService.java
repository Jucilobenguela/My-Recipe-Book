package academy.mindswap.my_recipe_book.service.ingredient;

import academy.mindswap.my_recipe_book.communication.dto.RequestRecipeDTO;
import academy.mindswap.my_recipe_book.model.entity.Ingredient;
import academy.mindswap.my_recipe_book.model.entity.Recipe;
import academy.mindswap.my_recipe_book.repository.ingredient.IngredientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;

    public void deleteIngredient(long ingredientId) {
        Ingredient ingredient = findIngredient(ingredientId);
        ingredientRepository.deleteById(ingredient.getId());

    }
    public void deleteListIngredient(List<Ingredient> ingredientList){
        for(Ingredient ingredient : ingredientList)
        {
            deleteIngredient(ingredient.getId());
        }
    }
    public List<Ingredient> listRequestIngredient(Recipe recipe,RequestRecipeDTO recipeDTO){
        List<Ingredient> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : recipeDTO.getIngredients()) {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setProduct(ingredient.getProduct());
            newIngredient.setQuantity(ingredient.getQuantity());
            newIngredient.setRecipe(recipe);
            ingredientList.add(newIngredient);
        }
      return ingredientList;
    }
    public Ingredient findIngredient(Long id){
        Ingredient ingredient=ingredientRepository.findById(id).orElse(null);
        if(ingredient == null){
            throw new NullPointerException("nao existe ingrediente com este id");
        }
        return ingredient;
    }
}
