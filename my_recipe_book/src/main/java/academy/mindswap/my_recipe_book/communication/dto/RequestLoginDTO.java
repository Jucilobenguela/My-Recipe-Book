package academy.mindswap.my_recipe_book.communication.dto;

import academy.mindswap.my_recipe_book.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public class RequestLoginDTO {
    private String email;
    private String password;

    public RequestLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

}
