package coe528.func.Bank;

import coe528.func.Users.Customer;
import coe528.func.Users.Manager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    Bank bank;
    Customer customer;
    Manager manager;
    @BeforeEach
    void setUp() {
        try {
            manager = new Manager("admin", "admin1234");
            bank = new Bank(manager);
            customer = new Customer("gabe", "g12345678");
            bank.addCustomer(customer, manager);
            bank.addAccount(customer, 100, manager);
            customer = new Customer("adam", "a12345678");
            bank.addCustomer(customer, manager);
            bank.addAccount(customer, 200, manager);

        }catch(Exception e){

        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findCustomer() throws Exception{
        Customer hold = bank.findCustomer(2);
        assertEquals("gabe", hold.getUsername());
        hold = bank.findCustomer("gabe");
        assertEquals("gabe", hold.getUsername());
    }

    @Test
    void findAccount() throws Exception{
        Account hold = bank.findAccount(2);
        assertEquals("adam", hold.getOwner().getUsername());
        hold = bank.findAccount(bank.findCustomer("gabe"));
        assertEquals("gabe", hold.getOwner().getUsername());
    }

}