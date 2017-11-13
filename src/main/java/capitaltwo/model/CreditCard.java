package capitaltwo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class CreditCard extends Card {

    private double monthlyLimit;
    private double monthlySpent;

    protected CreditCard() {}
    public CreditCard(CustomerAccount account,
                      int expirationYear,
                      int expirationMonth,
                      int expirationDay,
                      double monthlyLimit) {
        super(account, expirationYear, expirationMonth, expirationDay);
        this.monthlyLimit = monthlyLimit;
        this.monthlySpent = monthlyLimit;
    }

    public boolean charge(double cost) {
        if (monthlySpent - cost >= 0) {
            monthlySpent -= cost;
            return true;
        }
        return false;
    }

    public double getMonthlyLimit() { return this.monthlyLimit; }
    public void setMonthlyLimit(double limit) { this.monthlyLimit = limit; } 
    public double getMonthlySpent() { return this.monthlySpent; }
    public void setMonthlySpent(double spent) { this.monthlySpent = spent; }
}
