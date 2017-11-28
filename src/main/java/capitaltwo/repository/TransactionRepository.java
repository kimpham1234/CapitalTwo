package capitaltwo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	Transaction findByCard(@Param("card") Card card);
}
