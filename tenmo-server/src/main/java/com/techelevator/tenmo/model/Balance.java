package com.techelevator.tenmo.model;

import com.techelevator.tenmo.security.InsufficientFunds;

import java.math.BigDecimal;

public class Balance {

    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    //SEND - throws exception to insufficient funds (security) exception
    public void sendMoney(BigDecimal amount) throws InsufficientFunds {
        BigDecimal newBalance = new BigDecimal(String.valueOf(balance)).subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
            this.balance = newBalance;
        } else {
            throw new InsufficientFunds();
        }

    }
    //RECEIVE - exception not needed for receiving money
    public void receiveMoney(BigDecimal amount) {
        this.balance = new BigDecimal(String.valueOf(balance)).add(amount);
    }

}