package academy.mindswap.my_recipe_book.communication.dto;
import academy.mindswap.my_recipe_book.model.enun.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class RequestRecipeDTO {
    @NotBlank
    private String title;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotBlank
    private String deliveryMode;
    @NotBlank
    private int deliveryTime;
    @NotBlank
    private UserDTO userDTO;
    @NotBlank
    private List<RequestIngredientDTO> ingredientsDto;

    public RequestRecipeDTO(){

        ingredientsDto = new ArrayList<>();
    }
    public String getTitle() {
        return title;
    }
    public Category getCategory() {
        return category;
    }
    public String getDeliveryMode() {
        return deliveryMode;
    }
    public int getDeliveryTime() {
        return deliveryTime;
    }
    public UserDTO getUserDTO() {
        return userDTO;
    }
    public List<RequestIngredientDTO> getIngredientsDto() {
        return ingredientsDto;
    }

}
