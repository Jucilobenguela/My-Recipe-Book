package academy.mindswap.my_recipe_book.model.entity;
import academy.mindswap.my_recipe_book.model.enun.Category;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import org.hibernate.annotations.*;

import java.util.List;

@Entity
public class Recipe extends BaseEntity{
    private String title;
    private Category category;
    private String deliveryMode;
    private int deliveryTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Ingredient> ingredients;
    public Recipe(){}
    public Recipe(String title, Category category, String deliveryMode, int deliveryTime,User user, List<Ingredient> ingredients) {
        super();
        this.title = title;
        this.category = category;
        this.deliveryMode = deliveryMode;
        this.deliveryTime = deliveryTime;
        this.user=user;
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}