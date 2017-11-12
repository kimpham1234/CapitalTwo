package capitaltwo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
public class BusinessAccount extends Account {

    private Date partnershipExpiration;
    private String businessPosition;
    private String businessVerified;
    private Long businessId;

    protected BusinessAccount(){}

    public BusinessAccount(String loginId, String password, String phoneNo, String email,
            Date partnershipExpiration, String businessPosition, String businessVerified, Long businessId){

        super(loginId, password, phoneNo, email);
        this.partnershipExpiration = partnershipExpiration;
        this.businessPosition = businessPosition;
        this.businessVerified = businessVerified;
        this.businessId = businessId;
    }

    //getters & setters
    public void setPartnershipExpiration(Date ex){this.partnershipExpiration = ex;}
    public void setBusinessPosition(String pos){this.businessPosition = pos;}
    public void setBusinessVerified(String verified){this.businessVerified = verified;}
    public void setBusinessId(Long id){this.businessId = id;}

    public Date getPartnershipExpiration(){ return partnershipExpiration;}
    public String getBusinessPosition(){ return businessPosition;}
    public String getBusinessVerified(){ return businessVerified;}
    public Long getBusinessId(){ return businessId;}




   
}
