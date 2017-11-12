package capitaltwo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.sql.Date;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Card {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long cardNumber;
    private Date expirationDate;

    @ManyToOne(targetEntity=CustomerAccount.class)
    private CustomerAccount account;

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

    public CustomerAccount getAccount() {
        return this.account;
    }

    public void setCustomerAccount(CustomerAccount acc) {
        this.account = acc;
    }

    public abstract boolean charge(double val);
}

