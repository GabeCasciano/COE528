/** Bank App - COE528
 @author Gabriel Casciano, 500744076
 @since Nov,13, 2019
 @version 0.1
 */
package coe528.project.func.Bank;

import coe528.project.func.Users.*;

public class Account {
    private static int UPPER_LIMIT = 10000;
    private static int GOLD = 100000, PLATINUM = 20000;
    private static int MASTER_ACCOUNT_NUMBER = 1;
    private int accountNumber;
    private User owner;
    private double accountBalance;

    public Account(double initialBalance, User owner){
        this.accountBalance = initialBalance;
        this.owner = owner;
        this.accountNumber = MASTER_ACCOUNT_NUMBER++;
        toString();
    }

    public int getAccountNumber(){ return accountNumber; }
    public User getOwner(){ return owner; }
    public double getAccountBalance(){ return accountBalance; }


    public void deposit(double amount)throws Exception{
        if(amount <= 0)
            throw new IllegalArgumentException("negative amount");
        if(amount > UPPER_LIMIT)
            throw new Exception("deposit exceeds upper limit");
        this.accountBalance += amount;

        System.out.println(primarytoString());

    }
    public void withdraw(double amount)throws Exception{
        if(amount <= 0)
            throw new IllegalArgumentException("negative amount");
        if(amount > UPPER_LIMIT)
            throw new Exception("withdraw exceeds upper limit");
        if(this.accountBalance - amount <= 0)
            throw new Exception("not enough funds");

        this.accountBalance -= amount;

        System.out.println(primarytoString());
    }
    public void makePurchase(double amount)throws Exception{
        double costOfPurchase;
        if(amount >= PLATINUM)
            costOfPurchase = 0;
        else if(amount >= GOLD)
            costOfPurchase = 10;
        else
            costOfPurchase = 20;

        withdraw(amount+costOfPurchase);
        System.out.println("Cost of purchase: " + costOfPurchase);

    }
    public void setOwner(User owner, User admin)throws Exception{
        if(admin.getClass() != Manager.class)
            throw new IllegalAccessException("Not an admin");
        if(this.owner.equals(owner))
            throw new IllegalArgumentException("new Owner and old Owner are the same");

        this.owner = owner;
        System.out.println("New Owner: " + this.owner);
    }

    @Override
    public String toString(){
        return "Account Balance: $" + this.accountBalance + ", Account Number: " + accountNumber + ", Account Owner: " + owner.toString();
    }
    public String primarytoString(){
        return "Account Balance: $" + this.accountBalance;
    }
    public String toSql(){ return "INSERT INTO Accounts VALUES (" + this.accountNumber + "," + this.owner.getId() + "," + this.accountBalance; }
}
