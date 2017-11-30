package capitaltwo;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@NoRepositoryBean
public interface BaseCardRepository<T extends Card>
    extends JpaRepository<T,Long> {

    @Query("SELECT c FROM Card c WHERE c.id=?1")
    public Card findById(Long id);
}
