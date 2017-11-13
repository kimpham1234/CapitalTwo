package capitaltwo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionItemRepository
    extends JpaRepository<TransactionItem, TransactionItemId> {

}
