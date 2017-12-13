package capitaltwo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Business {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long businessId;
    private String name;
    private double rewardRate;

    @OneToMany(targetEntity=Transaction.class)
    private Set<Transaction> transactions;
    @OneToMany(targetEntity=BusinessAccount.class)
    private Set<BusinessAccount> accounts;

    protected Business() {}

    public Business(String name,
                    double rewardRate,
                    Set<Transaction> transactions,
                    Set<BusinessAccount> accounts) {
        this.name = name;
        this.rewardRate = rewardRate;
        this.transactions = transactions;
        this.accounts = accounts;
    }

    public Long getId() { return this.businessId; }
    public String getName() { return this.name; }
    public double getRewardRate() { return this.rewardRate; }
    public Set<Transaction> getTransactions() { return this.transactions; }
    public Set<BusinessAccount> getBusinessAccounts() { return this.accounts; }

    public void setName(String s) { this.name = s; }
    public void setRewardRate(double r) { this.rewardRate = r; }
    public void setTransactions(Set<Transaction> t) { this.transactions = t; }
    public void setAccounts(Set<BusinessAccount> a) { this.accounts = a; }
    public void addTransaction(Transaction t) { this.transactions.add(t); }
    public void addAccount(BusinessAccount a) { this.accounts.add(a); }
}
