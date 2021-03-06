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
public class CustomerAccount extends Account {

    private String firstName;
    private String middleName;
    private String lastName;
    private Ethnicity ethnicity;
    private Gender gender;
    private long rewardPoints;
    private long income;
    private int birthYear;
    private int birthMonth;
    private int birthDay;

    @OneToMany(targetEntity=Card.class)
    private Set<Card> customerCards;

    public CustomerAccount() {}
    public CustomerAccount(String loginId,
                           String password,
                           String phoneNo,
                           String email,
                           String firstName,
                           String middleName,
                           String lastName,
                           Ethnicity ethnicity,
                           Gender gender,
                           int birthYear,
                           int birthMonth,
                           int birthDay,
                           long income) {
        super(loginId, password, phoneNo, email);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.ethnicity = ethnicity;
        this.gender = gender;

        /* java.util.Date only starts at year 1900, use them only
         * as timestamps for transactions
         */
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.income = income;
        this.rewardPoints = 0;
    }

    public String getFirstName() { return this.firstName; };
    public void setFirstName(String firstName) { this.firstName = firstName; };

    public String getMiddleName() { return this.middleName; };
    public void setMiddleName(String middleName) { this.middleName = middleName; };

    public String getLastName() { return this.lastName; };
    public void setLastName(String lastName) { this.lastName = lastName; };

    public Ethnicity getEthnicity() { return this.ethnicity; };
    public void setEthnicity(Ethnicity ethnicity) { this.ethnicity = ethnicity; };

    public Gender getGender() { return this.gender; };
    public void setGender(Gender gender) { this.gender = gender; };

    public int getBirthDay() { return this.birthDay; };
    public int getBirthMonth() { return this.birthMonth; };
    public int getBirthYear() { return this.birthYear; };

    public void setBirthDate(int year, int month, int day) {
        this.birthYear = year;
        this.birthMonth = month;
        this.birthDay = day;
    }

    public long getIncome() { return this.income; };
    public void setIncome(long income) { this.income = income; };

    public long getRewardPoints() { return this.rewardPoints; };
    public void setRewardPoints(long points) { this.rewardPoints = points; };

    public Set<Card> getCards() { return this.customerCards; };
    public void addCard(Card card) { this.customerCards.add(card); };
    public void setCards(Set<Card> cards) { this.customerCards = cards; };

}

