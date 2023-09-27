package academy.mindswap.my_recipe_book.model.enun;

public enum UserRole {
    ADMIN("admin"),
    USER("user");
    private String role;
    UserRole(String role){
        this.role=role;
    }

    public String getRole() {
        return role;
    }
}
