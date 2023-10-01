package academy.mindswap.my_recipe_book.converts;

import academy.mindswap.my_recipe_book.dtos.request_dtos.RequestRecipeDTO;
import academy.mindswap.my_recipe_book.exception.userException.NotAuthenticateException;
import academy.mindswap.my_recipe_book.model.entity.Ingredient;
import academy.mindswap.my_recipe_book.model.entity.Recipe;
import academy.mindswap.my_recipe_book.model.entity.User;
import academy.mindswap.my_recipe_book.service.interfaces_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ConvertToRecipeDtoToRecipe {
    @Autowired
    UserService userService;

    public Recipe convertRequestRecipeDTOTORecipe(RequestRecipeDTO requestRecipeDTO) throws NotAuthenticateException {
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
