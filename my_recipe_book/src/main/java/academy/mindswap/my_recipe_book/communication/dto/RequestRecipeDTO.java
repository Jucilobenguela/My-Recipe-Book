package academy.mindswap.my_recipe_book.communication.dto;
import academy.mindswap.my_recipe_book.model.entity.Ingredient;
import academy.mindswap.my_recipe_book.model.enun.Category;
import java.util.ArrayList;
import java.util.List;

public class RequestRecipeDTO {
    private String title;
    private Category category;
    private String deliveryMode;
    private int deliveryTime;
    private UserDTO userDTO;
    private List<Ingredient> ingredients;

    public RequestRecipeDTO(){

        ingredients = new ArrayList<>();
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getDeliveryMode() {
        return deliveryMode;
    }
    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }
    public int getDeliveryTime() {
        return deliveryTime;
    }
    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    public UserDTO getUserDTO() {
        return userDTO;
    }
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
