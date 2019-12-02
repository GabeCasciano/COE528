/** Bank App - COE528
 @author Gabriel Casciano, 500744076
 @since Nov,13, 2019
 @version 0.1
 */
package coe528.func.Bank;

import coe528.func.Users.Customer;
import coe528.func.Users.Manager;
import coe528.func.Users.User;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Bank {

    //Database objects
    static final String DB_URL = "jdbc:sqlite:bank.db";//URL for the database
    private static Connection conn = null;
    private static Statement stmnt = null;

    //Lists of Users and accounts
    private static List<Account> accounts = new LinkedList<>();
    private static List<Customer> customers = new LinkedList<>();
    private static List<Manager> managers = new LinkedList<>();

    /** Default Constructor
     *
     * @param newManager the manager that we want to initialize the bank with
     */
    public Bank(Manager newManager){
        managers.add(newManager);
    }

    //Getters

    /** Use to get the list of accounts
     *
     * @return List<Account>, this.accounts
     */
    public List<Account> getAccounts(){ return accounts; }

    /** Use to get the list of customers
     *
     * @return List<Customer>, this.customers
     */
    public List<Customer> getCustomers(){ return new LinkedList<>(customers); }

    /** Use to get the list of managers
     *
     * @return List<Manager>, this.managers
     */
    public List<Manager> getManagers(){ return managers; }

    //administration

    /** Use to add a customer to the bank
     *
     * @param customer the customer to be added
     * @param admin the manager adding the customer to the bank
     * @throws Exception if admin is not a manager, if the customer is null, and if the customer already exists
     */
    public void addCustomer(Customer customer, Manager admin)throws Exception{
        if(admin.getClass() != Manager.class)
            throw new Exception("Not an admin");
        if(customer == null)
            throw new NullPointerException("customer is null");
        if(customers.contains(customer))
            throw new IllegalArgumentException("customer already exists");
        customers.add(customer);
    }

    /** Use to add a customer to the bank
     *
     * @param username the username for the new Customer
     * @param password the password for the new Customer
     * @param admin the Manager that is creating the new Customer
     * @throws Exception if admin is not a manager, if the customer is null, and if the customer already exists
     */
    public void addCustomer(String username, String password, Manager admin)throws Exception{
        if(admin.getClass() != Manager.class)
            throw new Exception("Not an admin");
        if(username == null)
            throw new NullPointerException("username is null");
        if(password == null)
            throw new NullPointerException("password is null");

        customers.add(new Customer(username, password));
    }

    /** Use to remove a customer from the bank
     *
     * @param customer the customer to be removed from the bank
     * @param admin the Manager that is removing the Customer
     * @throws Exception if admin is not a manager, and if the customer is null
     */
    public void removeCustomer(Customer customer, Manager admin)throws Exception{
        if(admin.getClass() != Manager.class)
            throw new Exception("Not an admin");
        if(customer == null)
            throw new NullPointerException("customer is null");
        for(Iterator<Customer> c = customers.iterator(); c.hasNext();){
            Customer cust = c.next();
            if(cust.equals(customer))
                c.remove();
        }
    }

    /** Use to add a manager to the bank
     *
     * @param newMan the new manager to be added to the bank
     * @param admin the Manager that is creating the new Manager
     * @throws Exception if admin is not a manager, and if the new manager is null
     */
    public void addManager(Manager newMan, Manager admin)throws Exception{
        if(admin.getClass() != Manager.class)
            throw new Exception("not an admin");
        if(newMan == null)
            throw new NullPointerException("New Manager is null");

        managers.add(new Manager(newMan));
        System.out.println("Manager Added: " + newMan.toString());
    }

    /** Use to add a new account to the bank
     *
     * @param customer the customer that is associated to the new account
     * @param initialBalance the initial balance of the new account
     * @param admin the Manager that is creating the new Manager
     * @throws Exception if admin is not a manager, if the customer is null, and if the initial balance is less than 0
     */
    public void addAccount(Customer customer, double initialBalance, Manager admin) throws Exception{
        if(admin.getClass() != Manager.class)
            throw new Exception("Not an admin");
        if(customer == null)
            throw new NullPointerException("customer is null");
        if(initialBalance < 0)
            throw new IllegalArgumentException("Initial balance cannot be less than 0");

        accounts.add(new Account(initialBalance, customer));
    }

    /** Use to remove an account from the bank
     *
     * @param account the account to be removed from the bank
     * @param admin the Manager that is removing the account from the bank
     * @throws Exception if the admin is not a manager, if the account is null
     */
    public void removeAccount(Account account, Manager admin) throws Exception{
        if(admin.getClass() != Manager.class)
            throw new Exception("Not an admin");
        if(account == null)
            throw new NullPointerException("account is null");
        for(Iterator<Account> c = accounts.iterator(); c.hasNext();){
            Account acnt = c.next();
            if(acnt.getOwner().equals(account.getOwner()))
                c.remove();
        }
    }

    //customer functions

    /** Use to make a deposit into a customers account
     *
     * @param account the account that funds are to be added to
     * @param user the user that is associated to the account
     * @param amount the amount of money to be deposited into the account
     * @throws Exception if the user does not exist, if the account does not exist, and any exceptions thrown from the account object
     */
    public void makeDeposit(Account account, User user, double amount) throws Exception{
        if(user == null || customers.contains(user) || managers.contains(user))
            throw new IllegalArgumentException("user does not exist");
        if(account == null || accounts.contains(account))
            throw new IllegalArgumentException("account does not exist");

        for(Iterator<Account> a = accounts.iterator(); a.hasNext();){
            Account acnt = a.next();
            if(acnt.getOwner().equals(user))
                acnt.deposit(amount);
        }
    }

    /** Use to withdraw money from the account
     *
     * @param account the account that funds with be withdrawn from
     * @param user the user that is associated with the account
     * @param amount the amount of funds to be withdrawn
     * @throws Exception if the user does not exist, if the account does not exist, and any exceptions thrown from the account object
     */
    public void makeWithdraw(Account account, User user, double amount) throws Exception{
        if(user == null || customers.contains(user) || managers.contains(user))
            throw new IllegalArgumentException("user does not exist");
        if(account == null || accounts.contains(account))
            throw new IllegalArgumentException("account does not exist");

        for(Iterator<Account> a = accounts.iterator(); a.hasNext();){
            Account acnt = a.next();
            if(acnt.getOwner().equals(user))
                acnt.withdraw(amount);
        }
    }

    /** Use to make a purchase with money from the account
     *
     * @param account the account that funds will be removed from
     * @param user the user that is associated with the account
     * @param amount the amount of funds to be removed
     * @throws Exception if the user does not exists, if the account does not exist, and any exceptions thrown from the account object
     */
    public void makePurchase(Account account, User user, double amount) throws Exception{
        if(user == null || customers.contains(user) || managers.contains(user))
            throw new IllegalArgumentException("user does not exist");
        if(account == null || accounts.contains(account))
            throw new IllegalArgumentException("account does not exist");

        for(Iterator<Account> a = accounts.iterator(); a.hasNext();){
            Account acnt = a.next();
            if(acnt.getOwner().equals(user))
                acnt.purchase(amount);
        }
    }

    //utils

    /** Use to find a Manager by id number
     *
     * @param id the id number associated to the manager that is being searched for
     * @return the Manager associated to the id number
     * @throws Exception if the Manager associated to the id number does not exist
     */
    public Manager findManager(int id) throws Exception{
        for(Iterator<Manager> m = managers.iterator(); m.hasNext();){
            Manager man = m.next();

            if(man.getId() == id)
                return new Manager(man);
        }
        throw new NullPointerException("Manager does not exist");
    }

    /** Use to find a Manager by username
     *
     * @param username the username associated to the manager that is being searched for
     * @return the Manager associated to the username
     * @throws Exception if the Manager associated to the username does not exist
     */
    public Manager findManager(String username)throws Exception{
        for(Iterator<Manager> m = managers.iterator(); m.hasNext();){
            Manager man = m.next();

            if(man.getUsername().equals(username))
                return new Manager(man);
        }
        throw new NullPointerException("Manager does not exist");
    }

    /** Use to find a custom by id number
     *
     * @param id the id number associated to the customer that is being searched for
     * @return the Customer associated to the id number
     * @throws Exception if the Customer associated to the id number does not exist
     */
    public Customer findCustomer(int id) throws Exception{
        for(Iterator<Customer> c = customers.iterator(); c.hasNext();){
            Customer cust = c.next();

            if(cust.getId() == id)
                return new Customer(cust);
        }
        throw new NullPointerException("Customer does not exist");
    }

    /** Use to find a customer by username
     *
     * @param username the username associated to the customer that is being searched for
     * @return the Customer associated to the username
     * @throws Exception if the Customer associated to the username does not exist
     */
    public Customer findCustomer(String username) throws Exception{
        for(Iterator<Customer> c = customers.iterator(); c.hasNext();){
            Customer cust = c.next();

            if(cust.getUsername().equals(username))
                return new Customer(cust);
        }
        throw new NullPointerException("Customer does not exist");
    }

    /** Use to find an account by customer
     *
     * @param customer the Customer that is associated to the account
     * @return the Account associated to the customer
     * @throws Exception if the account associated to the customer does not exist
     */
    public Account findAccount(Customer customer)throws Exception{
        for(Iterator<Account> a = accounts.iterator(); a.hasNext();){
            Account acnt = a.next();

            if(acnt.getOwner().equals(customer))
                return new Account(acnt);
        }
        throw new NullPointerException("Account does not exist");
    }

    /** Use to find an account by id number
     *
     * @param id the id number that is associated to the account
     * @return the Account associated to the id number
     * @throws Exception if the account associated to the id number does not exist
     */
    public Account findAccount(int id)throws Exception{
        for(Iterator<Account> a = accounts.iterator(); a.hasNext();){
            Account acnt = a.next();

            if(acnt.getAccountNumber() == id)
                return new Account(acnt);
        }
        throw new NullPointerException("Account does not exist");
    }

    //database

    /** Use to load the bank's data base
     *
     */
    public void loadBackUp(){

        try{
            conn = DriverManager.getConnection(DB_URL);
            stmnt = conn.createStatement();

            int id =0;
            String username = "";
            String password = "";

            //Read Managers
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Managers");
            while(rs.next()) {
                id = rs.getInt("id");
                username = rs.getString("username");
                password = rs.getString("passord");

                managers.add(new Manager(id, username, password));
            }
            rs = stmnt.executeQuery("SELECT * FROM Customers");
            while (rs.next()) {
                id = rs.getInt("id");
                username = rs.getString("username");
                password = rs.getString("password");

                customers.add(new Customer(id, username, password));
            }

            rs = stmnt.executeQuery("SELECT * FROM Accounts");
            while(rs.next()){
                username = rs.getString("owner_username");
                int balance = rs.getInt("account_balance");
                for(Iterator<Customer> c = customers.iterator(); c.hasNext();){
                    Customer cust = c.next();

                    accounts.add(new Account(balance, this.findCustomer(username)));
                }
            }

        }catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(stmnt != null)
                    conn.close();
            }
            catch (SQLException se){
                se.printStackTrace();
            }
        }
    }

    /** Use to back up the bank to a database
     *
     */
    public void backUp(){//back up to sql db
        try{

            conn = DriverManager.getConnection(DB_URL);
            stmnt = conn.createStatement();

            //empty out the db
            stmnt.executeUpdate("DELETE FROM Accounts");
            stmnt.executeUpdate("DELETE FROM Customers");
            stmnt.executeUpdate("DELETE FROM Managers");

            //write
            for(Iterator<Account> a = accounts.iterator(); a.hasNext();){
                Account acnt = new Account(a.next());
                stmnt.executeUpdate("INSERT INTO Accounts (owner_username, account_balance) VALUES ('" + acnt.getOwner().getUsername() + "'," + acnt.getAccountBalance() + ")");
            }

            //write
            for(Iterator<Customer> c = customers.iterator(); c.hasNext();){
                Customer cust = new Customer(c.next());
                stmnt.executeUpdate("INSERT INTO Customers (username, password) VALUES ( '"+ cust.getUsername() + "' , '" + cust.getPassword() + "' )");
            }

            //write
            for(Iterator<Manager> m = managers.iterator(); m.hasNext();){
                Manager man = new Manager(m.next());
                if(!man.getUsername().equals("admin")) {
                    stmnt.executeUpdate("INSERT INTO Managers (username, passord) VALUES ( '" +
                            man.getUsername() + "' , '" + man.getPassword() + "' )");
                }
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(stmnt != null)
                    conn.close();
            }
            catch (SQLException se){
                se.printStackTrace();
            }
        }
    }

}
