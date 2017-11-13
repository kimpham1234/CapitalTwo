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

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Card {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long cardNumber;
    private int expirationYear;
    private int expirationMonth;
    private int expirationDay;

    @ManyToOne(targetEntity=CustomerAccount.class)
    private CustomerAccount account;

    protected Card(){}

    public Card(CustomerAccount account, int year, int month, int day) {
        this.account = account;
        this.expirationYear = year;
        this.expirationMonth = month;
        this.expirationDay = day;
    }

    public void setExpirationDate(int year, int month, int day) {
        this.expirationYear = year;
        this.expirationMonth = month;
        this.expirationDay = day;
    };
    public int getExpirationYear() { return this.expirationYear; };
    public int getExpirationMonth() { return this.expirationMonth; };
    public int getExpirationDay() { return this.expirationDay; };


    public CustomerAccount getAccount() { return this.account; } 
    public void setCustomerAccount(CustomerAccount acc) { this.account = acc; }

    public abstract boolean charge(double val);
}

