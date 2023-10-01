package academy.mindswap.my_recipe_book.controllers;

import academy.mindswap.my_recipe_book.dtos.request_dtos.*;
import academy.mindswap.my_recipe_book.dtos.response_dtos.EmailResponseDTO;
import academy.mindswap.my_recipe_book.dtos.response_dtos.UserResponseJson;
import academy.mindswap.my_recipe_book.infra.TokenService;
import academy.mindswap.my_recipe_book.model.entity.User;
import academy.mindswap.my_recipe_book.repository.UserRepository;
import academy.mindswap.my_recipe_book.service.SendMessageQueueService;
import academy.mindswap.my_recipe_book.service.interfaces_services.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/my_recipe_book/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SendMessageQueueService sendMessageQueueService;
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
        sendMessageQueueService.publishExpense(registerUserDTO.getEmail());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id ){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UserUpdateDTO userDTO){
        userService.update(id, userDTO);
        return ResponseEntity.ok().build();

    }
    @PatchMapping("/updatePassword/{id}")
    public ResponseEntity updatePassword(@PathVariable Long id,
                                         @RequestBody RequestChangePassWordDTO requestChangePassWordDTO){
       userService.updatePassword(id, requestChangePassWordDTO);
      return   ResponseEntity.noContent().build();
    }
} 