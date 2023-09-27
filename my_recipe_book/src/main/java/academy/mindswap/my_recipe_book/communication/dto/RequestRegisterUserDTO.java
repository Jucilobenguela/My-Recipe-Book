package academy.mindswap.my_recipe_book.communication.dto;

import academy.mindswap.my_recipe_book.model.enun.UserRole;

public class RequestRegisterUserDTO {
    private String name;
    private String email;
    private String password;
    private UserRole role;

    public RequestRegisterUserDTO(String name, String email, String password, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role=userRole;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public UserRole getRole() {
        return role;
    }
}
