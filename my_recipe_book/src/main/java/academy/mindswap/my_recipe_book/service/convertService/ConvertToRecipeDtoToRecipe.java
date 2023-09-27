package academy.mindswap.my_recipe_book.service.convertService;

import academy.mindswap.my_recipe_book.communication.dto.RequestRecipeDTO;
import academy.mindswap.my_recipe_book.model.entity.Ingredient;
import academy.mindswap.my_recipe_book.model.entity.Recipe;
import academy.mindswap.my_recipe_book.model.entity.User;
import academy.mindswap.my_recipe_book.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ConvertToRecipeDtoToRecipe {
    @Autowired
    UserService userService;

    public Recipe convertRequestRecipeDTOTORecipe(RequestRecipeDTO requestRecipeDTO){
        if(requestRecipeDTO == null){
            throw new NullPointerException("receita vazia");
        }
        String email = userService.userAuthenticated();
        User user = userService.findByEmail(email);

        List<Ingredient> ingredientList = new ArrayList<>();

        Recipe recipe = new Recipe(requestRecipeDTO.getTitle(), requestRecipeDTO.getCategory()
                , requestRecipeDTO.getDeliveryMode(),requestRecipeDTO.getDeliveryTime()
                , user
                ,ingredientList);
        if(requestRecipeDTO.getUserDTO().getEmail() == null){
            throw new NullPointerException("Email vazio");

        }
        if(!email.equals(requestRecipeDTO.getUserDTO().getEmail())){
            throw new RuntimeException("Email invalido");
        }
        return recipe;
    }
}
