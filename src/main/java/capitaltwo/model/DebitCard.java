package capitaltwo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class DebitCard extends Card {

    private double balance;
    public DebitCard() {}
    public DebitCard(CustomerAccount account,
                     int year,
                     int month,
                     int day,
                     double balance) {
        super(account, year, month, day);
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
