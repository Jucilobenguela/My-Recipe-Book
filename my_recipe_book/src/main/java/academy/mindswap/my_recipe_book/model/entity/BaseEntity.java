package academy.mindswap.my_recipe_book.model.entity;

import jakarta.persistence.*;

import java.time.LocalTime;
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private LocalTime createData = LocalTime.now();

    public long getId() {
        return Id;
    }

    public LocalTime getCreateData() {
        return createData;
    }

    public void setId(long id) {
        Id = id;
    }
}
