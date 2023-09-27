package academy.mindswap.my_recipe_book.repository.user;

import academy.mindswap.my_recipe_book.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    UserDetails findByEmail(String email);
}
