package academy.mindswap.my_recipe_book.controller;

import academy.mindswap.my_recipe_book.communication.dto.RequestRecipeDTO;
import academy.mindswap.my_recipe_book.model.entity.User;
import academy.mindswap.my_recipe_book.service.queueService.SendMessageQueueService;
import academy.mindswap.my_recipe_book.service.recipe.RecipeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/my_recipe_book/recipe")
public class RecipeController {
    @Autowired
    RecipeService recipeService;
    @Autowired
    SendMessageQueueService sendMessageQueueService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RequestRecipeDTO recipeDTO){
        if(recipeDTO == null){
            return ResponseEntity.badRequest().build();
        }
        recipeService.registerRecipe(recipeDTO);
        sendMessageQueueService.publishExpense(recipeDTO.getUserDTO().getEmail());
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity updateRecipe(@PathVariable Long id, @RequestBody  RequestRecipeDTO requestRecipeDTO){
        recipeService.updateRecipe(id,requestRecipeDTO);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecipe (@PathVariable Long id){

       if(id == null){
            return ResponseEntity.badRequest().build();
        }
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

}
