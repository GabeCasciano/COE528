package coe528.project.func.Bank;

import coe528.project.func.Users.*;

public class Account {
    private static int MASTER_ACCOUNT_NUMBER = 0;
    private int accountNumber;
    private User owner;
    private double accountBalance;

    public Account(double initialBalance, User owner){
        this.accountBalance = initialBalance;
        this.owner = owner;
    }
}
