package academy.mindswap.my_recipe_book.model.entity;

import academy.mindswap.my_recipe_book.enums.UserRole;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class User extends BaseEntity implements UserDetails {
    private String userName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole roles;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Recipe> recipes;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Connection> connection;



    public User(String name, String email, String password, UserRole roles){
        this.userName = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles == UserRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getUsername() {
        return this.userName;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Connection> getConnection() {
        return connection;
    }
    public void setConnection(List<Connection> connection) {
        this.connection = connection;
    }
}
