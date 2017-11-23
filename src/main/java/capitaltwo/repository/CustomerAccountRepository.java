package capitaltwo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CustomerAccountRepository extends BaseAccountRepository<CustomerAccount> {
	CustomerAccount findByEmail(@Param("email") String email);

    @Query(value="SELECT * from customer_account", nativeQuery=true)
    List<Object> getCustomers();
}
