package academy.mindswap.my_recipe_book.test.service;

import academy.mindswap.my_recipe_book.communication.dto.RequestRecipeDTO;
import academy.mindswap.my_recipe_book.communication.dto.UserDTO;
import academy.mindswap.my_recipe_book.model.entity.Ingredient;
import academy.mindswap.my_recipe_book.model.entity.Recipe;
import academy.mindswap.my_recipe_book.repository.recipe.RecipeRepository;
import academy.mindswap.my_recipe_book.repository.user.UserRepository;
import academy.mindswap.my_recipe_book.service.convertService.ConvertToRecipeDtoToRecipe;
import academy.mindswap.my_recipe_book.service.ingredient.IngredientService;
import academy.mindswap.my_recipe_book.service.recipe.RecipeService;
import academy.mindswap.my_recipe_book.service.user.authentication.AuthService;
import academy.mindswap.my_recipe_book.service.user.register.UserServiceRegister;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    UserServiceRegister userServiceRegister;
    @Mock
    private AuthService authService;
    @Mock
    private IngredientService ingredientService;
    @Mock
    ConvertToRecipeDtoToRecipe convertToRecipeDtoToRecipe;
    @Mock
    Ingredient ingredient;
    @InjectMocks
    private RecipeService recipeService;
    @InjectMocks
    Recipe recipe;

    @Test
    @DisplayName("Register a Recipe in the database")
    void registerRecipe_shouldSaveRecipe() {
        RequestRecipeDTO requestRecipeDTO = new RequestRecipeDTO();
        Recipe recipe = new Recipe();
        when(convertToRecipeDtoToRecipe.convertRequestRecipeDTOTORecipe(requestRecipeDTO))
                .thenReturn(recipe);
        when(ingredientService.listRequestIngredient(recipe, requestRecipeDTO))
                .thenReturn(new ArrayList<>());
        recipeService.registerRecipe(requestRecipeDTO);
        verify(recipeRepository, times(1)).save(recipe);
    }
    @Test
    @DisplayName("Update a Recipe in the database")
    void updateRecipe_shouldUpdateRecipe() {

    }
    @Test
    @DisplayName("Delete a Recipe from the database")
    void deleteRecipe_shouldDeleteRecipe() {
        long recipeId = 1L;
        Recipe recipe = new Recipe();
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
        recipeService.deleteRecipe(recipeId);
        verify(recipeRepository, times(1)).deleteById(recipeId);
    }
}
