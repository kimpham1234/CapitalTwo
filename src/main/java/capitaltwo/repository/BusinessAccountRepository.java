package capitaltwo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface BusinessAccountRepository extends BaseAccountRepository<BusinessAccount> {
	BusinessAccount findByEmail(@Param("email") String email);
}

