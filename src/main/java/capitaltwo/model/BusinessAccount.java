package capitaltwo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
public class BusinessAccount extends Account {

    private int expirationYear;
    private int expirationMonth;
    private int expirationDay;
    private String businessPosition;
    private boolean businessVerified;
    @ManyToOne(targetEntity=Business.class)
    private Business business;

    protected BusinessAccount(){}

    public BusinessAccount(String loginId,
                           String password,
                           String phoneNo,
                           String email,
                           int expirationYear,
                           int expirationMonth,
                           int expirationDay,
                           String businessPosition,
                           boolean businessVerified,
                           Business business) {

        super(loginId, password, phoneNo, email);
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
        this.expirationDay = expirationDay;
        this.businessPosition = businessPosition;
        this.businessVerified = businessVerified;
        this.business = business;
    }

    //getters & setters
    public void setExpirationDate(int year, int month, int day) {
        this.expirationYear = year;
        this.expirationMonth = month;
        this.expirationDay = day;
    }
    public void setBusinessPosition(String pos){this.businessPosition = pos;}
    public void setBusinessVerified(boolean verified){this.businessVerified = verified;}
    public void setBusiness(Business b){this.business = b;}

    public int getExpirationYear() { return this.expirationYear; }
    public int getExpirationMonth() { return this.expirationMonth; }
    public int getExpirationDay() { return this.expirationDay; }
    public String getBusinessPosition(){ return businessPosition; }
    public boolean getBusinessVerified(){ return businessVerified;}
    public Business getBusiness(){ return business;}




   
}
