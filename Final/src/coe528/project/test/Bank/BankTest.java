package coe528.project.test.Bank;

import coe528.project.func.Bank.Account;
import coe528.project.func.Bank.Bank;
import coe528.project.func.Users.Customer;
import coe528.project.func.Users.Manager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class BankTest {

    Bank bank;
    Manager manager;
    Customer customer;
    Account account;

    boolean SETUP_TEST_FLAG = true;
    boolean TEST_FLAG = false;

    @Test
    void setup() {
        try{
            manager = new Manager("admin", "admin123");
            customer = new Customer("JohnDoe", "JD123456");
            bank = new Bank(manager);
            account = new Account(200, customer);

            if(TEST_FLAG){
                String temp = "admin";
                //test manager
                assertEquals(1, manager.getId());
                assertEquals(temp, manager.getUsername());
                temp = "admin123";
                assertEquals(temp, manager.getPassword());
                assertEquals(temp.hashCode(), manager.getPasswordHash());

                //test customer
                temp = "JohnDoe";
                assertEquals(2, customer.getId());
                assertEquals(temp, customer.getUsername());
                temp = "JD123456";
                assertEquals(temp, customer.getPassword());
                assertEquals(temp.hashCode(), customer.getPasswordHash());

                //test bank
                assertEquals(0, bank.getCustomers().size());
                assertEquals(1, bank.getManagers().size());
                assertEquals(0, bank.getAccounts().size());

                //test account
                assertEquals(1, account.getAccountNumber());
                assertEquals(customer, account.getOwner());
                assertEquals(200.00, account.getAccountBalance());
            }

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

    }

    void tearDown(){
        bank = null;
        account = null;
        customer = null;
        manager = null;
    }

    @org.junit.jupiter.api.Test
    void addCustomer() {
        try {
            //necessary variable instantiation
            setup();
            bank.addCustomer(customer, manager);
            assertEquals(1, bank.getCustomers().size());//there should only be 1 customer
            assertEquals(0, bank.getAccounts().size());//there should be 0 accounts
            assertEquals(1, bank.getManagers().size());//there should only be 1 manager
            assertEquals(customer.getId(), bank.getCustomers().get(0).getId());//customs should be equal to the only other customer

        }catch (Exception e){//if it throws an exceptions it has failed
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void addMultipleCustomer(){
        try{

            setup();

            for(int i = 0; i<10; i++){
                customer.setUsername(customer.getUsername()+i);
                bank.addCustomer(customer, manager);

                assertEquals(i + 1, bank.getCustomers().size());
            }

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @org.junit.jupiter.api.Test
    void removeCustomer() {
        try {

            addCustomer();//add initial customer, and initialize variables

            bank.removeCustomer(customer, manager);

            assertEquals(0, bank.getCustomers().size());//there should be 0 customers
            assertEquals(0, bank.getAccounts().size());//there should be 0 accounts
            assertEquals(1, bank.getManagers().size());//there should still be 1 manager

            try {
                bank.getCustomers().get(0);
                fail();
            }catch (Exception e){
                assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            }

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void removeMultipleCustomers(){
        try{

            setup();
            Customer temp = new Customer(customer);
            for(int i = 0 ; i < 10 ; i++) {
                customer.setUsername(customer.getUsername()+i);
                bank.addCustomer(customer, manager);
            }
            assertEquals(10, bank.getCustomers().size());
            customer = new Customer(temp);
            for(int i = 0; i < 10; i++) {
                customer.setUsername(customer.getUsername()+i);
                bank.removeCustomer(customer, manager);

                assertEquals(9-i, bank.getCustomers().size());
            }
        }catch (Exception e){
            e.printStackTrace();
             fail();
        }

    }

    @org.junit.jupiter.api.Test
    void addAccount() {
        try{
            setup();
            bank.addAccount(customer, 200.00, manager);

            assertEquals(1, bank.getAccounts().size());
            assertEquals(200.0, bank.getAccounts().get(0).getAccountBalance());
            assertEquals(customer.getId(), bank.getAccounts().get(0).getOwner().getId());
            assertEquals(customer.getUsername(), bank.getAccounts().get(0).getOwner().getUsername());

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @org.junit.jupiter.api.Test
    void removeAccount() {
        try{
            addAccount();
            bank.removeAccount(customer, manager);

            assertEquals(0, bank.getAccounts().size());

            addAccount();
            bank.removeAccount(account, manager);

            assertEquals(0, bank.getAccounts().size());
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

    }

    @org.junit.jupiter.api.Test
    void makeDeposit() {
        try{
            addAccount();

            bank.makeDeposit(account, customer, 100.0);
            assertEquals(300.0, bank.getAccounts().get(0).getAccountBalance());
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @org.junit.jupiter.api.Test
    void makeWithdraw() {
        try{
            addAccount();

            bank.makeDeposit(account, customer, 100.0);
            assertEquals(300.0, bank.getAccounts().get(0).getAccountBalance());

            bank.makeWithdraw(account, customer, 100.0);
            assertEquals(200.0, bank.getAccounts().get(0).getAccountBalance());

            try{
                bank.makeWithdraw(account, customer, 300);
                fail();
            }catch (Exception e){
                System.out.println("properly threw exception");
            }
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @org.junit.jupiter.api.Test
    void makePurchase() {
        try{
            addAccount();

            bank.makeDeposit(account, customer, 100.0);
            assertEquals(300.0, bank.getAccounts().get(0).getAccountBalance());

            bank.makePurchase(account, customer, 100.0);
            assertEquals(200 - 20, bank.getAccounts().get(0).getAccountBalance());//silver level customer

            try{
                bank.makePurchase(account, customer, 300);
                fail();
            }catch (Exception e){
                System.out.println("properly threw exception");
            }
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

    }

    @Test
    void backUp(){
        try{
            setup();
            bank.addCustomer(customer, manager);
            bank.addAccount(customer, 100, manager);
            bank.backUp();

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void loadBackUp(){
        try{
            setup();
            bank.loadBackUp();

            assertEquals(1, bank.getCustomers().size());
            assertEquals(2, bank.getManagers().size());//must initialize the bank with manager, thus, base val is 1 not 0
            assertEquals(1, bank.getAccounts().size());
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }
}