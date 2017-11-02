package com.greglturnquist.payroll;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class DebitCard extends Card {

    private double balance;

    protected DebitCard(){}

    public DebitCard(Date expirationDate, String loginId, double balance) {
        super(expirationDate, loginId);
        this.balance = balance;
    }

    public double getBalance() { return this.balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
