package capitaltwo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.repository.query.Param;
=======
import org.springframework.data.jpa.repository.Query;
>>>>>>> f15041f8eb6facc46887d23a051f690273b29b58
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CustomerAccountRepository extends BaseAccountRepository<CustomerAccount> {
	CustomerAccount findByEmail(@Param("email") String email);

    @Query(value="SELECT * from customer_account", nativeQuery=true)
    List<Object> getCustomers();
}
