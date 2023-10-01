package academy.mindswap.my_recipe_book.test.repository;

import academy.mindswap.my_recipe_book.model.entity.User;
import academy.mindswap.my_recipe_book.enums.UserRole;
import academy.mindswap.my_recipe_book.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRepositoryTest {
    @Test
    public void createUser_withValidData_ReturnsUser() {
        // Crie um mock do UserRepository
        UserRepository userRepository = mock(UserRepository.class);

        // Crie um usuário com dados válidos
        User user = new User("Jucilo", "jucilo@gmail.com", "12345", UserRole.USER);

        // Quando o método save do UserRepository for chamado com o usuário, retorne o mesmo usuário
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userRepository.save(user);
        assertNotNull(createdUser); // Verifique se o usuário não é nulo
    }
}
