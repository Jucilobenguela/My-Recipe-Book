package academy.mindswap.my_recipe_book.service.ingredient;

import academy.mindswap.my_recipe_book.communication.dto.RequestIngredientDTO;
import academy.mindswap.my_recipe_book.communication.dto.RequestRecipeDTO;
import academy.mindswap.my_recipe_book.exception.ingredientException.NotFoundIngredientException;
import academy.mindswap.my_recipe_book.model.entity.Ingredient;
import academy.mindswap.my_recipe_book.model.entity.Recipe;
import academy.mindswap.my_recipe_book.repository.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;

    public void deleteIngredient(long ingredientId) {
        try {
            Ingredient ingredient = findIngredient(ingredientId);
            ingredientRepository.deleteById(ingredient.getId());
        } catch (NotFoundIngredientException e) {
            throw new RuntimeException(e);
        }

    }
    public void deleteListIngredient(List<Ingredient> ingredientList){
        for(Ingredient ingredient : ingredientList)
        {
            deleteIngredient(ingredient.getId());
        }
    }
    public List<Ingredient> listRequestIngredient(Recipe recipe,RequestRecipeDTO recipeDTO){
        List<Ingredient> ingredientList = new ArrayList<>();
        for (RequestIngredientDTO ingredientDTO : recipeDTO.getIngredientsDto()) {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setProduct(ingredientDTO.getProduct());
            newIngredient.setQuantity(ingredientDTO.getQuantity());
            newIngredient.setRecipe(recipe);
            ingredientList.add(newIngredient);
        }
      return ingredientList;
    }
    public Ingredient findIngredient(Long id) throws NotFoundIngredientException {
        Ingredient ingredient=ingredientRepository.findById(id).orElse(null);
        if(ingredient == null){
            throw new NotFoundIngredientException("Ingredient not found");
        }
        return ingredient;
    }
}
