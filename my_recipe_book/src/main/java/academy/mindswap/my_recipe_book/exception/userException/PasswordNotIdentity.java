package academy.mindswap.my_recipe_book.exception.userException;

public class PasswordNotIdentity extends RuntimeException{
    public PasswordNotIdentity() {
        super("Password did n´t identity");
    }
}
