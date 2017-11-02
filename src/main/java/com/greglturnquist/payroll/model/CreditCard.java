package com.greglturnquist.payroll;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
public class CreditCard extends Card {

    private double monthlyLimit;

    protected CreditCard(){}
    
    public CreditCard(Date expirationDate, String loginId, double monthlyLimit)
   	{
        super(expirationDate, loginId);
        this.monthlyLimit = monthlyLimit;
    }

    
    public double getMonthlyLimit() { return this.monthlyLimit; }
    public void setMonthlyLimit(double limit) { this.monthlyLimit = limit; } 
}
