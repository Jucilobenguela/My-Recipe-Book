package academy.mindswap.my_recipe_book.service.interfaces_services;

import academy.mindswap.my_recipe_book.dtos.request_dtos.RequestChangePassWordDTO;
import academy.mindswap.my_recipe_book.dtos.request_dtos.RequestRegisterUserDTO;
import academy.mindswap.my_recipe_book.dtos.request_dtos.UserDTO;
import academy.mindswap.my_recipe_book.dtos.request_dtos.UserUpdateDTO;
import academy.mindswap.my_recipe_book.exception.userException.NotAuthenticateException;
import academy.mindswap.my_recipe_book.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User get(String email);

    User getById(Long id);

    void create(RequestRegisterUserDTO registerUserDTO);

    void updatePassword(Long id, RequestChangePassWordDTO passWordDTO);

    User update(Long id, UserUpdateDTO userDTO);

    void delete(Long id);
    User findByEmail(String email);
    String userAuthenticated() throws NotAuthenticateException;

}