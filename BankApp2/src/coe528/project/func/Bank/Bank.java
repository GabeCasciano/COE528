/** Bank App - COE528
 @author Gabriel Casciano, 500744076
 @since Nov,13, 2019
 @version 0.1
 */
package coe528.project.func.Bank;

import coe528.project.func.Users.Customer;
import coe528.project.func.Users.Manager;
import coe528.project.func.Users.User;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Bank {


    static final String DB_URL = "jdbc:sqlite:/home/student1/gcascian/COE528/BankApp2/src/bank.db";
    // More database bs
    private static Connection conn = null;
    private static Statement stmnt = null;

    private static List<Account> accounts = new LinkedList<>();
    private static List<Customer> customers = new LinkedList<>();
    private static List<Manager> managers = new LinkedList<>();

    public Bank(Manager newManager){
        managers.add(newManager);
    }
    //get & set
    public List<Account> getAccounts(){ return accounts; }
    public List<Customer> getCustomers(){ return new LinkedList<>(customers); }
    public List<Manager> getManagers(){ return managers; }

    //administration
    public void addCustomer(Customer customer, Manager admin)throws Exception{
        if(admin.getClass() != Manager.class)
            throw new Exception("Not an admin");
        if(customer == null)
            throw new NullPointerException("customer is null");
        if(customers.contains(customer))
            throw new IllegalArgumentException("customer already exists");
        customers.add(customer);
    }
    public void addCustomer(String username, String password, Manager admin)throws Exception{
        if(admin.getClass() != Manager.class)
            throw new Exception("Not an admin");
        if(username == null)
            throw new NullPointerException("username is null");
        if(password == null)
            throw new NullPointerException("password is null");

        customers.add(new Customer(username, password));
    }

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
    public void addAccount(Customer customer, double initialBalance, Manager admin) throws Exception{
        if(admin.getClass() != Manager.class)
            throw new Exception("Not an admin");
        if(customer == null)
            throw new NullPointerException("customer is null");
        if(initialBalance < 0)
            throw new IllegalArgumentException("Initial balance cannot be less than 0");

        accounts.add(new Account(initialBalance, customer));
    }
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
    public void removeAccount(Customer customer, Manager admin) throws Exception{
        if(admin.getClass() != Manager.class)
            throw new Exception("Not an admin");
        if(customer == null)
            throw new NullPointerException("customer is null");
        for(Iterator<Account> c = accounts.iterator(); c.hasNext();){
            Account acnt = c.next();
            if(acnt.getOwner().equals(customer))
                c.remove();
        }
    }

    //customer functions
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
    
    public Customer findCustomer(String username){
        for(Iterator<Customer> c = customers.iterator(); c.hasNext();){
            Customer cust = c.next();
            
            if(cust.getUsername().equals(username))
                return cust;
        }
        return null;
    }
    //utils
    public Account findAccount(Customer customer){
        for(Iterator<Account> a = accounts.iterator(); a.hasNext();){
            Account acnt = a.next();

            if(acnt.getOwner().equals(customer))
                return acnt;
        }
        System.out.println("No account found");
        return null;
    }

    //database
    public void loadBackUp(){

        try{
            conn = DriverManager.getConnection(DB_URL);
            stmnt = conn.createStatement();

            //Read Managers
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Managers");
            while(rs.next())
                managers.add(new Manager(rs.getInt("id"), rs.getString("username"),
                        rs.getString("passord")));

            rs = stmnt.executeQuery("SELECT * FROM Customers");
            while (rs.next())
                customers.add(new Customer(rs.getInt("id"), rs.getString("username"),
                        rs.getString("password")));

            rs = stmnt.executeQuery("SELECT * FROM Accounts");
            while(rs.next()){
                for(Iterator<Customer> c = customers.iterator(); c.hasNext();){
                    Customer cust = c.next();
                    if(rs.getInt("owner_id") == cust.getId())
                        accounts.add(new Account(rs.getInt("id"), rs.getInt("account_balance"),
                                cust));
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
                stmnt.executeUpdate("INSERT INTO Accounts (id, owner_id, account_balance) VALUES (" + acnt.getAccountNumber() + "," +
                        acnt.getOwner().getId() + "," + acnt.getAccountBalance() + ")");
            }

            //write
            for(Iterator<Customer> c = customers.iterator(); c.hasNext();){
                Customer cust = new Customer(c.next());
                stmnt.executeUpdate("INSERT INTO Customers (id, username, password) VALUES (" + cust.getId() + ", '" +
                        cust.getUsername() + "' , '" + cust.getPassword() + "' )");
            }

            //write
            for(Iterator<Manager> m = managers.iterator(); m.hasNext();){
                Manager man = new Manager(m.next());
                stmnt.executeUpdate("INSERT INTO Managers (id, username, passord) VALUES (" + man.getId() + ", '" +
                        man.getUsername() + "' , '" + man.getPassword() + "' )");
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
