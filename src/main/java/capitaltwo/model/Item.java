package capitaltwo;

import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long itemId;
    private String name;
    private String description;

    @ManyToOne(targetEntity=Category.class)
    private Category category;

    @OneToMany(mappedBy="item")
    private Set<TransactionItem> transactionItems;

    protected Item() { }

    public Item(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Long getId() { return this.itemId; }

    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public Category getCategory() { return this.category; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(Category c) { this.category = c; }
}
