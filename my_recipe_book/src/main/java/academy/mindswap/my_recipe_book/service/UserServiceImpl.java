package academy.mindswap.my_recipe_book.service;

import academy.mindswap.my_recipe_book.dtos.request_dtos.RequestChangePassWordDTO;
import academy.mindswap.my_recipe_book.dtos.request_dtos.RequestRegisterUserDTO;
import academy.mindswap.my_recipe_book.dtos.request_dtos.UserDTO;
import academy.mindswap.my_recipe_book.dtos.request_dtos.UserUpdateDTO;
import academy.mindswap.my_recipe_book.exception.userException.NotAuthenticateException;
import academy.mindswap.my_recipe_book.exception.userException.PasswordNotIdentity;
import academy.mindswap.my_recipe_book.exception.userException.UserNotFoundDataBaseException;
import academy.mindswap.my_recipe_book.model.entity.User;
import academy.mindswap.my_recipe_book.repository.UserRepository;
import academy.mindswap.my_recipe_book.configuration.authentication.AuthService;
import academy.mindswap.my_recipe_book.service.interfaces_services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;
    @Autowired
    PasswordEncoder passwordEncoder;

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
        return userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundDataBaseException());
    }
    @Override
    public void create(RequestRegisterUserDTO registerUserDTO) {
        String encryptedPassword = encode(registerUserDTO.getPassword());
        User newUser= new User(registerUserDTO.getName(), registerUserDTO.getEmail(), encryptedPassword, registerUserDTO.getRole());
      userRepository.save(newUser);
    }
    @Override
    public User update(Long id, UserUpdateDTO user) {
        User userUpdate =  getById(id);
        BeanUtils.copyProperties(user, userUpdate);
       return userRepository.save(userUpdate);
    }
    private String encode (String password){
        return passwordEncoder.encode(password);
    }
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public User findByEmail(String email) {
        return (User)userRepository.findByEmail(email);
    }

    @Override
    public String userAuthenticated() throws NotAuthenticateException {
        return authService.userAuthenticated();
    }
    @Override
    public void updatePassword(Long id, RequestChangePassWordDTO passWordDTO){
        User user = getById(id);
        Boolean identityPassword = passwordEncoder.matches(passWordDTO.getCurrentPassWord(),user.getPassword());
        if(!identityPassword){
            throw new PasswordNotIdentity();
        }
        String encryptedPassword = encode(passWordDTO.getNewPassword());
       user.setPassword(encryptedPassword);
        userRepository.save(user);
    }
}
