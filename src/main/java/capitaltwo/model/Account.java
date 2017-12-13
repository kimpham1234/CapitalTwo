package capitaltwo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long accountId;
    private String loginId;
    private String password;
    private String phoneNo;
    private String email;

    protected Account(){}

    public Account(String loginId, String password, String phoneNo, String email) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public void setLoginId(String loginId){
        this.loginId = loginId;
    }
    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLoginId(){
        return loginId;
    }
    public String getPassword(){
        return password;
    }
    public String getPhoneNo(){
        return phoneNo;
    }
    public String getEmail(){
        return email;
    }
}
