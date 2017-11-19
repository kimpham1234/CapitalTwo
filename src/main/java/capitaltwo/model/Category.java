package capitaltwo;

import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long categoryId;
    private String name;
    private String description;

    @OneToMany
    private Set<Item> items;

    protected Category() {};
    
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String desc) { this.description = desc; }
}
