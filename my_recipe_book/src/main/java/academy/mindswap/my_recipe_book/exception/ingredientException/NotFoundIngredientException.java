package academy.mindswap.my_recipe_book.exception.ingredientException;

public class NotFoundIngredientException extends RuntimeException{
    public NotFoundIngredientException(String message) {
        super(message);
    }
    public NotFoundIngredientException() {
        super("Ingredient not found");
    }
}
