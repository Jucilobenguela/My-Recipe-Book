package academy.mindswap.my_recipe_book.service;

import academy.mindswap.my_recipe_book.dtos.request_dtos.RequestIngredientDTO;
import academy.mindswap.my_recipe_book.dtos.request_dtos.RequestRecipeDTO;
import academy.mindswap.my_recipe_book.exception.ingredientException.NotFoundIngredientException;
import academy.mindswap.my_recipe_book.model.entity.Ingredient;
import academy.mindswap.my_recipe_book.model.entity.Recipe;
import academy.mindswap.my_recipe_book.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        Ingredient ingredient=ingredientRepository.findById(id).orElseThrow(()-> new NotFoundIngredientException());

        return ingredient;
    }
}
