package capitaltwo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Table(name="DebitCard")
public class DebitCard extends Card {

    private double balance;
    public DebitCard() {}
    public DebitCard(Date expirationDate, double balance) {
        super(expirationDate);
        this.balance = balance;
    }

    public boolean charge(double cost) {
        if (balance - cost >= 0) {
            balance -= cost;
            return true;
        }
        return false;
    }

    public double getBalance() { return this.balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
