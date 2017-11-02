package com.greglturnquist.payroll;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Card {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long cardNumber;
    private Date expirationDate;
    private String loginId;

    protected Card(){}

    public Card(Date expirationDate, String loginId) {
        this.expirationDate = expirationDate;
        this.loginId = loginId;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public String getLoginId() {
        return this.loginId;
    }
}
