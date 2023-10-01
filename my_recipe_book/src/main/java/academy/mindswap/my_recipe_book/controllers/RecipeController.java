package academy.mindswap.my_recipe_book.controllers;

import academy.mindswap.my_recipe_book.dtos.request_dtos.RequestRecipeDTO;
import academy.mindswap.my_recipe_book.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my_recipe_book/recipe")
public class RecipeController {
    @Autowired
    RecipeService recipeService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RequestRecipeDTO recipeDTO){
        if(recipeDTO == null){
            return ResponseEntity.badRequest().build();
        }
        recipeService.registerRecipe(recipeDTO);
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
