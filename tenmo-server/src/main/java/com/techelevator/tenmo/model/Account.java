package com.techelevator.tenmo.model;

public class Account {

    private int accountId;
    private int userId;
    private Balance balance;

    //CONSTRUCTOR
    public Account(int accountId, int userId, Balance balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
    }

    //GETTERS
    public int getAccountId() {
        return accountId;
    }

    public int getUserId() {
        return userId;
    }
    public Balance getBalance() {
        return balance;
    }

    //SETTERS
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

}
