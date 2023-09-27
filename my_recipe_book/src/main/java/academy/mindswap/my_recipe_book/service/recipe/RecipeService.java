package academy.mindswap.my_recipe_book.service.recipe;

import academy.mindswap.my_recipe_book.communication.dto.RequestRecipeDTO;
import academy.mindswap.my_recipe_book.model.entity.Ingredient;
import academy.mindswap.my_recipe_book.model.entity.Recipe;
import academy.mindswap.my_recipe_book.repository.recipe.RecipeRepository;
import academy.mindswap.my_recipe_book.repository.user.UserRepository;
import academy.mindswap.my_recipe_book.service.convertService.ConvertToRecipeDtoToRecipe;
import academy.mindswap.my_recipe_book.service.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    ConvertToRecipeDtoToRecipe convertToRecipeDtoToRecipe;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientService ingredientService;

    public void registerRecipe(RequestRecipeDTO requestRecipeDTO){
        if(requestRecipeDTO==null){
            throw new NullPointerException("receita vazia");
        }
        Recipe recipe = convertToRecipeDtoToRecipe.convertRequestRecipeDTOTORecipe(requestRecipeDTO);
        recipe.setIngredients(ingredientService.listRequestIngredient(recipe,requestRecipeDTO));
        recipeRepository.save(recipe);
    }
    public void updateRecipe(Long id, RequestRecipeDTO recipeDTO){
        if (recipeDTO== null){
            throw new NullPointerException();
        }
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        recipe.setId(id);
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setCategory(recipeDTO.getCategory());
        recipe.setDeliveryMode(recipeDTO.getDeliveryMode());
        recipe.setDeliveryTime(recipeDTO.getDeliveryTime());
        List<Ingredient> ingredientList = ingredientService.listRequestIngredient(recipe, recipeDTO);
        for (Ingredient ingredient : recipe.getIngredients()) {
            //podia fazer comparacao de cada propriedade do igrediente e apagar e pagar somente aquele que nao for igual a da requisicao
            ingredientService.deleteIngredient(ingredient.getId());
        }
        recipe.setIngredients(ingredientList);
        recipeRepository.save(recipe);
    }
    public void deleteRecipe(long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe == null) {
            throw new NullPointerException( "Recita com este Id não existe");
        }
        ingredientService.deleteListIngredient(recipe.getIngredients());
        recipeRepository.deleteById(recipeId);
        if(recipe == null){
            System.out.println("receita apagada");
        }
    }
}
