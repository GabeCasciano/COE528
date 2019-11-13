package coe528.project.func.Users;
/*
    All purchases must be greater than 50$

    There needs to be:
        Platinum = acnt bal. >= 20k, free
        Gold = acnt bal. >= 10k, 10$ fee per purchase
        Silver = acnt bal. < 10k, 20$ Fee per purchase
 */


public class Customer extends User{
    public Customer(String username, String password){ super(username, password);}
}
