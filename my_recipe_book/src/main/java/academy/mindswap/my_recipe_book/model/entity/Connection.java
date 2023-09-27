package academy.mindswap.my_recipe_book.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Connection extends BaseEntity{
    private long connectionUserId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
