package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    // amount of transfer
    // account from (user_id)
    // account too


    public Transfer(int accountFrom, int accountInto, BigDecimal amount) {

        this.accountFrom = accountFrom;
        this.accountInto = accountInto;
        this.amount = amount;
    }
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountInto() {
        return accountInto;
    }

    public void setAccountInto(int accountInto) {
        this.accountInto = accountInto;
    }

    private int accountFrom;
    private int accountInto;



}
