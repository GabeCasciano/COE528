/** Bank App - COE528
 @author Gabriel Casciano, 500744076
 @since Nov, 25, 2019
 @version 1.0
 */
package coe528.func.Bank;


import coe528.func.Users.Customer;
import coe528.func.Users.User;


/**
 * Account class
 */
public class Account extends Customer {
    private static int UPPER_LIMIT = 1000000;
    private static int GOLD = 10000, PLATINUM = 20000;
    private static int MASTER_ACCOUNT_NUMBER = 1;
    private int accountNumber;
    private User owner;
    private double accountBalance;

    /** Empty Constructor
     *
     * @throws Exception the exception comes from the User initialization
     */
    public Account() throws Exception{
        this.accountBalance = 0;
        this.owner = new Customer();
        this.accountNumber = MASTER_ACCOUNT_NUMBER++;
    }

    /** Default constructor
     *
     * @param initialBalance the balance the account will start with
     * @param owner the owner of the account, of type User (either a manager or customer)
     * @throws Exception the exception comes from the User initialization
     */
    public Account(double initialBalance, User owner)throws Exception{
        this.accountBalance = initialBalance;
        this.owner = owner;
        this.accountNumber = MASTER_ACCOUNT_NUMBER++;
    }

    /** Manual Constructor
     *
     * @param id the id that the account should have
     * @param bal the balance that the account with begin with
     * @param owner the owner of the account, of type User
     * @throws Exception the exception comes from the User initialization
     */
    public Account(int id, double bal, User owner)throws Exception{
        this.accountNumber = id;
        this.accountBalance = bal;
        this.owner = owner;
    }

    /** Copy constructor
     *
     * @param a the account to be copied
     * @throws Exception the exception comes from the User initialization
     */
    public Account(Account a)throws Exception{
        this.owner = a.getOwner();
        this.accountBalance = a.getAccountBalance();
        this.accountNumber = a.getAccountNumber();
    }

    /** Use to get the account's number
     *
     * @return int, this.accountNumber
     */
    public int getAccountNumber(){ return accountNumber; }

    /** Use to get the account's owner
     *
     * @return User, this.owner
     */
    public User getOwner(){ return owner; }

    /** Use to get the account's balance
     *
     * @return int, this.accountBalance
     */
    public double getAccountBalance(){ return accountBalance; }

    /** Use to deposit money into the account
     *
     * @param amount the amount of money to be deposited into THIS account
     * @throws Exception thrown if the amount entered is, negative or greater than the upper limit, 10k
     */
    public void deposit(double amount)throws Exception{
        if(amount <= 0)
            throw new IllegalArgumentException("negative amount");
        if(amount > UPPER_LIMIT)
            throw new Exception("deposit exceeds upper limit");
        this.accountBalance += amount;

        System.out.println(primarytoString());

    }

    /** Use to withdraw money from the account
     *
     * @param amount the amount of money to be withdrawn from THIS account
     * @throws Exception thrown if the amount entered is, negative, greater than the upper limit, or reduces THIS account balance to zero, or less.
     */
    public void withdraw(double amount)throws Exception{
        if(amount <= 0)
            throw new IllegalArgumentException("negative amount");
        if(amount > UPPER_LIMIT)
            throw new Exception("withdraw exceeds upper limit");
        if(this.accountBalance - amount <= 0)
            throw new IllegalArgumentException("not enough funds");

        this.accountBalance -= amount;

        System.out.println(primarytoString());
    }

    /** Use to make a purchase with the money in the account
     *
     * @param amount the amount of money to be withdrawn from THIS account
     * @throws Exception thrown if the amount entered is, negative, greater than the upper limit, or reduces THIS account balance to zero, or less.
     */
    public void purchase(double amount)throws Exception{
        double costOfPurchase;
        if(accountBalance >= PLATINUM)
            costOfPurchase = 0;
        else if(accountBalance >= GOLD)
            costOfPurchase = 10;
        else
            costOfPurchase = 20;

        withdraw(amount+costOfPurchase);
        System.out.println("Cost of purchase: " + costOfPurchase);

    }

    /** Get string representation of the Account
     *
     * @return returns all of the information for this account
     */
    @Override
    public String toString(){
        return "Account Balance: $" + this.accountBalance + ", Account Number: " + accountNumber + ", Account Owner: " + owner.toString();
    }

    /** Get the "primary" account information
     *
     * @return returns only the balance as a string
     */
    public String primarytoString(){
        return "Account Balance: $" + this.accountBalance;
    }

}
