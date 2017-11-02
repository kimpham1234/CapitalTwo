package com.greglturnquist.payroll;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;


@Entity
public class CustomerAccount extends Account {
	private int rewardPoints;
	private String name;
	private String ethnicity;
	private String gender;
	private Date dob;
	private double income;

    protected CustomerAccount(){}

	public CustomerAccount(String loginId, String password, String phoneNo, String email, String name,
					 	String ethnicity, String gender, Date dob, double income) {
        super(loginId, password, phoneNo, email);
        this.rewardPoints = 0;
        this.name = name;
        this.ethnicity = ethnicity;
        this.gender = gender;
        this.dob = dob;
        this.income = income;
    }

    public void updateRewardPoints(int amount){
    	rewardPoints += amount;
    }

    //setters & getters
    public void setRewardPoints(int rewardPoints){
    	this.rewardPoints = rewardPoints;
    }
    public void setName(String name){
    	this.name = name;
    }
    public void setEthnicity(String ethnicity){
    	this.ethnicity = ethnicity;
    }
    public void setGender(String gender){
    	this.gender = gender;
    }
    public void setDob(Date dob){
    	this.dob = dob;
    }
    public void setIncome(double income){
    	this.income = income;
    }

    public int getRewardPoints(){
    	return rewardPoints;
    }
    public String getName(){
    	return name;
    }
    public String getEthnicity(){
    	return ethnicity;
    }
    public String getGender(){
    	return gender;
    }
    public Date getDob(){
    	return dob;
    }
    public double getIncome(){
    	return income;
    }
}
