package academy.mindswap.my_recipe_book.service.user.authentication;
import academy.mindswap.my_recipe_book.infra.security.TokenService;
import academy.mindswap.my_recipe_book.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
    private Authentication authentication;
    @Autowired
    TokenService tokenService;
    public boolean isUserAuthenticated(){
        authentication = getUserAuthentication();
        if(authentication!=null){
        if (authentication.isAuthenticated()) {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                User user = (User) authentication.getPrincipal();
                if (!user.getUsername().equals("anonymousUser")) {
                    return true;
                }
            }
        }
        }
        return false;
    }
    public  String userAuthenticated(){
       authentication = getUserAuthentication();
        if (!isUserAuthenticated()){
            throw new NullPointerException("Usuario não autenticado");
        }
       User user = (User) authentication.getPrincipal();
        return user.getEmail();
    }

    private Authentication getUserAuthentication(){
        if (SecurityContextHolder.getContext().getAuthentication()==null){
            throw new NullPointerException("Usuario nullo");
        }
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
