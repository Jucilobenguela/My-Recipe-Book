package academy.mindswap.my_recipe_book.communication.dto;

import academy.mindswap.my_recipe_book.model.entity.User;

public class UserDTO {
    private String name;
    private String email;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email) {
        this.name = name;
        this.email = email;
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
    public UserDTO convertUser_To_UserDto(User user){
      return   new UserDTO(user.getId(),user.getUsername(), user.getEmail());
    }

}
