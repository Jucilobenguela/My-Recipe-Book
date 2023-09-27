package academy.mindswap.my_recipe_book.communication.response;

import academy.mindswap.my_recipe_book.model.entity.User;

public record UserResponseJson( String name, String email, String password) {
    public UserResponseJson(User user){
        this(user.getUsername(), user.getEmail(), user.getPassword());

    }

}
