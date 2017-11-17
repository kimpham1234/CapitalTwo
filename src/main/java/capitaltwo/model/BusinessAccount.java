package capitaltwo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class BusinessAccount extends Account {

    private int expirationYear;
    private int expirationMonth;
    private int expirationDay;
    private String businessPosition;
    private String businessVerified;
    private Long businessId;

    protected BusinessAccount(){}

    public BusinessAccount(String loginId,
                           String password,
                           String phoneNo,
                           String email,
                           int expirationYear,
                           int expirationMonth,
                           int expirationDay,
                           String businessPosition,
                           String businessVerified,
                           Long businessId) {

        super(loginId, password, phoneNo, email);
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
        this.expirationDay = expirationDay;
        this.businessPosition = businessPosition;
        this.businessVerified = businessVerified;
        this.businessId = businessId;
    }

    //getters & setters
    public void setExpirationDate(int year, int month, int day) {
        this.expirationYear = year;
        this.expirationMonth = month;
        this.expirationDay = day;
    }
    public void setBusinessPosition(String pos){this.businessPosition = pos;}
    public void setBusinessVerified(String verified){this.businessVerified = verified;}
    public void setBusinessId(Long id){this.businessId = id;}

    public int getExpirationYear() { return this.expirationYear; }
    public int getExpirationMonth() { return this.expirationMonth; }
    public int getExpirationDay() { return this.expirationDay; }
    public String getBusinessPosition(){ return businessPosition; }
    public String getBusinessVerified(){ return businessVerified;}
    public Long getBusinessId(){ return businessId;}




   
}
