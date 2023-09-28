package academy.mindswap.my_recipe_book.communication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserCreateDTO {

    @NotBlank
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank
    @Size(min = 3, max = 250)
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Password must contain at least 8 characters, one uppercase, one lowercase and one number")
    private String password;

    private String retypePassword;

    public UserCreateDTO() {
    }


    public UserCreateDTO(String name, String email, String password, String retypePassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.retypePassword = retypePassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
