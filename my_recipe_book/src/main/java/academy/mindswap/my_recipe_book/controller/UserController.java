package academy.mindswap.my_recipe_book.controller;

import academy.mindswap.my_recipe_book.communication.dto.RequestLoginDTO;
import academy.mindswap.my_recipe_book.communication.dto.RequestRegisterUserDTO;
import academy.mindswap.my_recipe_book.communication.response.EmailResponseDTO;
import academy.mindswap.my_recipe_book.communication.response.UserResponseJson;
import academy.mindswap.my_recipe_book.infra.security.TokenService;
import academy.mindswap.my_recipe_book.model.entity.User;
import academy.mindswap.my_recipe_book.repository.user.UserRepository;
import academy.mindswap.my_recipe_book.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/my_recipe_book")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @GetMapping
    public ResponseEntity get(){
        List<UserResponseJson> users = this.userRepository.findAll()
                .stream()
                .map(request -> new UserResponseJson(request))
                .toList();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/login")
    public ResponseEntity login(@RequestBody @Valid RequestLoginDTO loginDTO){
        Authentication userNamePassword
                = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword());
        Authentication auth = this.authenticationManager.authenticate(userNamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer" + token);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new EmailResponseDTO(token));
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RequestRegisterUserDTO registerUserDTO){
        if(this.userRepository.findByEmail(registerUserDTO.getEmail())!=null){
            return ResponseEntity.badRequest().build();
        }
        userService.create(registerUserDTO);
        return ResponseEntity.ok().build();
    }
} 