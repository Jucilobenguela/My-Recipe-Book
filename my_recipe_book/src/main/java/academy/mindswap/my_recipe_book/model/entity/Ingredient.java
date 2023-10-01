package academy.mindswap.my_recipe_book.model.entity;

import academy.mindswap.my_recipe_book.dtos.request_dtos.RequestIngredientDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ingredient extends BaseEntity{
    private String product;
    private String quantity;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Ingredient(){}
    public Ingredient(RequestIngredientDTO ingredientDTO) {
        this.product = ingredientDTO.getProduct();
        this.quantity = ingredientDTO.getQuantity();
    }

    public String getProduct() {
        return product;
    }

    public String getQuantity() {
        return quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
