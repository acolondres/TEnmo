package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    // amount of transfer
    // account from (user_id)
    // account to ETC. follow ERD
    private BigDecimal amount;
    private int accountFrom;
    private int accountInto;
    private int transferStatusId;
    private int transferTypeId;
    private int transferId;


    public Transfer(int accountFrom, int accountInto, BigDecimal amount, int transferStatusId, int transferTypeId, int transferId) {

        this.accountFrom = accountFrom;
        this.accountInto = accountInto;
        this.amount = amount;
        this.transferStatusId = transferStatusId;
        this.transferTypeId= transferTypeId;
        this.transferId = transferId;
    }


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

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }




}
