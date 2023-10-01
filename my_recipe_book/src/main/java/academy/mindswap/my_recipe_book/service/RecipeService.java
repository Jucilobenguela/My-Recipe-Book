package academy.mindswap.my_recipe_book.service;

import academy.mindswap.my_recipe_book.dtos.request_dtos.RequestRecipeDTO;
import academy.mindswap.my_recipe_book.exception.recipeException.NullRecipeException;
import academy.mindswap.my_recipe_book.exception.recipeException.RecipeNotFoundDataBaseException;
import academy.mindswap.my_recipe_book.exception.userException.NotAuthenticateException;
import academy.mindswap.my_recipe_book.model.entity.Ingredient;
import academy.mindswap.my_recipe_book.model.entity.Recipe;
import academy.mindswap.my_recipe_book.repository.RecipeRepository;
import academy.mindswap.my_recipe_book.converts.ConvertToRecipeDtoToRecipe;
import academy.mindswap.my_recipe_book.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class RecipeService {
    @Autowired
    ConvertToRecipeDtoToRecipe convertToRecipeDtoToRecipe;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientService ingredientService;

    public void registerRecipe(RequestRecipeDTO requestRecipeDTO){
       try {
           if(requestRecipeDTO==null){
               throw new NullRecipeException("Recipe is null");
           }
           Recipe recipe = convertToRecipeDtoToRecipe.convertRequestRecipeDTOTORecipe(requestRecipeDTO);
           recipe.setIngredients(ingredientService.listRequestIngredient(recipe,requestRecipeDTO));
           recipeRepository.save(recipe);
       }  catch (NotAuthenticateException | NullRecipeException e) {
           log.error(e.getMessage());
       }
    }
    public void updateRecipe(Long id, RequestRecipeDTO recipeDTO) {
        try {
           Recipe recipe = checkPropertyUpdates(id, recipeDTO);
       List<Ingredient> ingredientList = ingredientService.listRequestIngredient(recipe, recipeDTO);
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredientService.deleteIngredient(ingredient.getId());
            }
            recipe.setIngredients(ingredientList);
            recipeRepository.save(recipe);
        } catch (RecipeNotFoundDataBaseException e) {
            log.error(e.getMessage());
        }
    }
    public void deleteRecipe(long recipeId) {
        try {
          Recipe recipe = findRecipeByIdDataBase(recipeId);
            ingredientService.deleteListIngredient(recipe.getIngredients());
            recipeRepository.deleteById(recipeId);
        } catch (RecipeNotFoundDataBaseException e) {
            log.error(e.getMessage());
        }
    }
    private Recipe findRecipeByIdDataBase(Long id) throws RecipeNotFoundDataBaseException {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if (recipe == null) {
            throw new RecipeNotFoundDataBaseException( "Recipe with this Id was not found");
        }
        return recipe;
    }
    private Recipe checkPropertyUpdates(Long id, RequestRecipeDTO recipeDTO) throws RecipeNotFoundDataBaseException {
        Recipe recipe = findRecipeByIdDataBase(id);
        if (recipeDTO.getDeliveryTime() != recipe.getDeliveryTime()) {
            recipe.setDeliveryTime(recipeDTO.getDeliveryTime());
        }
        if (recipeDTO.getCategory() != recipe.getCategory()) {
            recipe.setCategory(recipeDTO.getCategory());
        }
        if (!recipeDTO.getDeliveryMode().equals(recipe.getDeliveryMode())) {
            recipe.setDeliveryMode(recipeDTO.getDeliveryMode());
        }
        if (!recipeDTO.getTitle().equals(recipe.getTitle())) {
            recipe.setTitle(recipeDTO.getTitle());
        }
        recipe.setId(id);
        return recipe;
    }
}
