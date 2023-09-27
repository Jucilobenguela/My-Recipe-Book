package academy.mindswap.my_recipe_book.service.user.register;

import academy.mindswap.my_recipe_book.communication.dto.RequestRegisterUserDTO;
import academy.mindswap.my_recipe_book.communication.dto.UserCreateDTO;
import academy.mindswap.my_recipe_book.communication.dto.UserDTO;
import academy.mindswap.my_recipe_book.model.entity.User;
import academy.mindswap.my_recipe_book.repository.user.UserRepository;
import academy.mindswap.my_recipe_book.service.user.UserService;
import academy.mindswap.my_recipe_book.service.user.authentication.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceRegister implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;
    UserDTO userDto;
    @Override
    public List<User> getAll() {
        return null;
    }
    @Override
    public User get(String email) {
        return null;
    }
    @Override
    public User getById(Long id) {
        return null;
    }
    @Override
    public void create(RequestRegisterUserDTO registerUserDTO) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerUserDTO.getPassword());
        User newUser= new User(registerUserDTO.getName(), registerUserDTO.getEmail(), encryptedPassword, registerUserDTO.getRole());
      userRepository.save(newUser);
    }

    @Override
    public List<UserDTO> create(List<UserCreateDTO> users) {
        return null;
    }

    @Override
    public User update(Long id, User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findByEmail(String email) {
        return (User)userRepository.findByEmail(email);
    }

    @Override
    public String userAuthenticated() {
        return authService.userAuthenticated();
    }
}
