package capitaltwo;

import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import java.util.Set;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long transactionId;
    private Date date; // timestamp of purchase
    private double cost;
    private String state;
    private String city;

    @ManyToOne(targetEntity=Card.class)
    private Card card;
    //private Business business;
    @OneToMany(mappedBy="transaction")
    private Set<TransactionItem> transactionItems;

    protected Transaction() {
        System.out.println("Creating null transaction");
    }

    public Transaction(Date date,
                       String state,
                       String city,
                       Card card,
                       //Business business,
                       Set<TransactionItem> items
    ) {
        System.out.println("creating transaction via not null");
        this.date = date;
        this.state = state;
        this.city = city;
        this.card = card;
        //this.business = business;
        this.transactionItems = items;
        this.cost = computeCost();
    }

    private double computeCost() {
        if (this.transactionItems == null) return 0;
        double cost = 0;
        for (TransactionItem i : this.transactionItems) {
            cost += i.getQuantity()*i.getUnitPrice();
        }
        return cost;
    }

    public Long getId() { return this.transactionId; }
    
    public void setDate(Date d) { this.date = d; }
    public void setState(String state) { this.state = state; }
    public void setCity(String city) { this.city = city; }
    public void setCard(Card card) { this.card = card; }
    public void setTransactionItems(Set<TransactionItem> items) {
        this.transactionItems = items;
        this.cost = computeCost();
    }

    public Date getDate() { return this.date; }
    public String getState() { return this.state; }
    public String getCity() { return this.city; }
    public Card getCard() { return this.card; }
    public Set<TransactionItem> getTransactionItems() { return this.transactionItems; }

    @Override
    public String toString() {
        return "Date: " + this.date + " state: " + this.state + " id: " +
            this.transactionId;
    }

}
