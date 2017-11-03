package capitaltwo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
//import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
import java.sql.Date;

@Entity
@Table(name="Card")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Card {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long cardNumber;
    private Date expirationDate;

    protected Card(){}

    public Card(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }
}

