package academy.mindswap.my_recipe_book.exception.userException;

public class UserNotFoundDataBaseException extends RuntimeException{
    public UserNotFoundDataBaseException(String message) {
        super(message);
    }

    public UserNotFoundDataBaseException() {
        super("user not found");
    }
}


