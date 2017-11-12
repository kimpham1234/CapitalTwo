package capitaltwo;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;

@NoRepositoryBean
public interface BaseAccountRepository<T extends Account>
    extends JpaRepository<T,Long> {


}
