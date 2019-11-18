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
import java.util.LinkedList;
import java.util.List;

public class Bank {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/BankApp_db";
    //  Database credentials
    static final String USER = "SA";
    static final String PASS = "Friday13";
    // More database bs
    private Connection conn = null;
    private Statement stmnt = null;

    private static List<Account> accounts = new LinkedList<>();
    private static List<Customer> customers = new LinkedList<>();
    private static List<Manager> managers = new LinkedList<>();

    public Bank(Manager newManager){
        managers.add(newManager);
    }

    //get & set
    public List<Account> getAccounts(){ return accounts; }
    public List<Customer> getCustomers(){ return customers; }
    public List<Manager> getManagers(){ return managers; }

    //administration
    public void addCustomer(Customer customer, Manager admin)throws Exception{
        if(admin.getClass() != Manager.class)
            throw new IllegalCallerException("Not an admin");
        if(customer == null)
            throw new NullPointerException("customer is null");
        if(customers.contains(customer))
            throw new IllegalArgumentException("customer already exists");
        customers.add(new Customer(customer));
    }
    public void removeCustomer(Customer customer, Manager admin)throws Exception{
        if(admin.getClass() != Manager.class)
            throw new IllegalCallerException("Not an admin");
        if(customer == null)
            throw new NullPointerException("customer is null");
        if(!customers.remove(customer))
            throw new IllegalArgumentException("Customer does not exist");
    }
    public String addAccount(Customer customer, double initialBalance, Manager admin) throws Exception{
        if(admin.getClass() != Manager.class)
            throw new IllegalCallerException("Not an admin");
        if(customer == null)
            throw new NullPointerException("customer is null");
        if(initialBalance < 0)
            throw new IllegalArgumentException("Initial balance cannot be less than 0");

        Account a = new Account(initialBalance, customer);
        accounts.add(a);
        return a.toString();
    }
    public void removeAccount(Account account, Manager admin) throws Exception{
        if(admin.getClass() != Manager.class)
            throw new IllegalCallerException("Not an admin");
        if(account == null)
            throw new NullPointerException("account is null");
        if(!accounts.remove(account))
            throw new IllegalArgumentException("account does not exist");
    }
    public void removeAccount(Customer customer, Manager admin) throws Exception{
        if(admin.getClass() != Manager.class)
            throw new IllegalCallerException("Not an admin");
        if(customer == null)
            throw new NullPointerException("customer is null");
        for(Account a : accounts){
            if(a.getOwner() == customer)
                accounts.remove(a);
        }
    }

    //customer functions
    public void makeDeposit(Account account, User user, double amount) throws Exception{
        if(user == null || customers.contains(user) || managers.contains(user))
            throw new IllegalArgumentException("user does not exist");
        if(account == null || accounts.contains(account))
            throw new IllegalArgumentException("account does not exist");

        Account a = accounts.get(accounts.indexOf(account));
        if(a.getOwner() != user)
            throw new IllegalCallerException("User does not match user on the account");

        a.deposit(amount);
    }
    public void makeWithdraw(Account account, User user, double amount) throws Exception{
        if(user == null || customers.contains(user) || managers.contains(user))
            throw new IllegalArgumentException("user does not exist");
        if(account == null || accounts.contains(account))
            throw new IllegalArgumentException("account does not exist");

        Account a = accounts.get(accounts.indexOf(account));
        if(a.getOwner() != user)
            throw new IllegalCallerException("User does not match user on the account");

        a.withdraw(amount);
    }
    public void makePurchase(Account account, User user, double amount) throws Exception{
        if(user == null || customers.contains(user) || managers.contains(user))
            throw new IllegalArgumentException("user does not exist");
        if(account == null || accounts.contains(account))
            throw new IllegalArgumentException("account does not exist");

        Account a = accounts.get(accounts.indexOf(account));
        if(a.getOwner() != user)
            throw new IllegalCallerException("User does not match user on the account");

        a.makePurchase(amount);
    }


    //database
    public void loadBackUp(){
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT id, usrnm, pswrd, isManager FROM Users ");

            while(rs.next()){
                if(rs.getBoolean("isManager"))
                    managers.add(new Manager(rs.getInt("id"), rs.getString("usrnm"), rs.getString("pswrd")));
                else
                    customers.add(new Customer(rs.getInt("id"), rs.getString("usrnm"), rs.getString("pswrd")));
                rs.close();
            }

            rs = stmnt.executeQuery("SELECT id, clientid, accntBalance FROM Accounts");

            while (rs.next()){
                for(Customer c : customers) {
                    if(rs.getInt("id") == c.getId())
                        accounts.add(new Account(rs.getInt("accntBalance"), c));
                    else
                        throw new Exception("no customer with that id");
                }
            }
        }
        catch (Exception e){

        }
    }
    public void backUp(){//back up to sql db
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmnt = conn.createStatement();

            for(Account a : accounts)
                stmnt.executeUpdate(a.toSql());
            for(User u : customers)
                stmnt.executeUpdate(u.toSql());
            for(User u : managers)
                stmnt.executeUpdate(u.toSql());
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
