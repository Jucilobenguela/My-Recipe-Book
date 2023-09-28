package academy.mindswap.my_recipe_book.service.user;

import academy.mindswap.my_recipe_book.communication.dto.RequestRegisterUserDTO;
import academy.mindswap.my_recipe_book.communication.dto.UserCreateDTO;
import academy.mindswap.my_recipe_book.communication.dto.UserDTO;
import academy.mindswap.my_recipe_book.exception.userException.NotAuthenticateException;
import academy.mindswap.my_recipe_book.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User get(String email);

    User getById(Long id);

    void create(RequestRegisterUserDTO registerUserDTO);

    List<UserDTO> create(List<UserCreateDTO> users);

    User update(Long id, User user);

    void delete(Long id);
    User findByEmail(String email);
    String userAuthenticated() throws NotAuthenticateException;

}