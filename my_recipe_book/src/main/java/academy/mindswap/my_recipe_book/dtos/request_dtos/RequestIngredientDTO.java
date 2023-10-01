package academy.mindswap.my_recipe_book.dtos.request_dtos;

import jakarta.validation.constraints.NotBlank;

public class RequestIngredientDTO {
    @NotBlank
    private String product;
    @NotBlank
    private String quantity;

    public String getProduct() {
        return product;
    }

    public String getQuantity() {
        return quantity;
    }
}
