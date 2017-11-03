package capitaltwo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
//import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="CustomerAccount")
public class CustomerAccount extends Account {

    private String firstName;
    private String middleName;
    private String lastName;
    private Ethnicity ethnicity;
    private Gender gender;
    private Date birthday;
    private long rewardPoints;
    private long income;

    //private Set<Card> customerCards;

    public CustomerAccount() {}
    public CustomerAccount(String firstName,
                           String middleName,
                           String lastName,
                           Ethnicity ethnicity,
                           Gender gender,
                           Date birthday,
                           long income) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.ethnicity = ethnicity;
        this.gender = gender;
        this.birthday = birthday;
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

    public Date getBirthday() { return this.birthday; };
    public void setBirthday(Date birthday) { this.birthday = birthday; };

    public long getIncome() { return this.income; };
    public void setIncome(long income) { this.income = income; };

    public long getRewardPoints() { return this.rewardPoints; };
    public void setRewardPoints(long points) { this.rewardPoints = points; };

    //@OneToMany(mappedBy="card")
    //public Set<Card> getCards() { return this.customerCards; };
}

