/** Bank App - COE528
 @author Gabriel Casciano, 500744076
 @since Nov,13, 2019
 @version 0.1
 */
package coe528.project.func.Bank;

import coe528.project.func.Users.Customer;
import coe528.project.func.Users.Manager;
import coe528.project.func.Users.User;

import java.util.LinkedList;
import java.util.List;

public class Bank {
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
    public void addCustomer(Customer customer, Manager admin){}
    public void removeCustomer(Customer customer, Manager admin){}
    public void addAccount(Account account, Manager admin){}
    public void removeAccount(Account account, Manager admin){}

    //customer functions
    public void makeDeposit(Account account, User user, double amount){}
    public void makeWithdraw(Account account, User user, double amount){}
    public void makePurchase(Account account, User user, double amount){}

    //functionality
    public double roundVal(double amount){ double scale = Math.pow(10, 2); return Math.round(amount * scale)/scale; }

}
