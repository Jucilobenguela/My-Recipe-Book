package academy.mindswap.my_recipe_book.configuration.authentication;

import academy.mindswap.my_recipe_book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException  {
        UserDetails user = userRepository.findByEmail(userEmail);
        if (user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }
}
