package capitaltwo;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;

@NoRepositoryBean
public interface BaseCardRepository<T extends Card>
    extends JpaRepository<T,Long> {


}
