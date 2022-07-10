package com.techelevator.tenmo.model;

import java.math.BigDecimal;

//USE BIG DECIMAL TO GET ACCURATE BALANCE
public class Balance {

    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    //SEND - throws exception to insufficient funds (security) exception
    public void sendMoney(BigDecimal amount) throws InsufficientFundsException {
        BigDecimal newBalance = new BigDecimal(String.valueOf(balance)).subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
            this.balance = newBalance;
        } else {
            throw new InsufficientFundsException();
        }

    }
    //RECEIVE - exception not needed for receiving money
    public void receiveMoney(BigDecimal amount) {
        this.balance = new BigDecimal(String.valueOf(balance)).add(amount);
    }

}