package capitaltwo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i WHERE i.id=?1")
    public Item findById(Long id);
}
