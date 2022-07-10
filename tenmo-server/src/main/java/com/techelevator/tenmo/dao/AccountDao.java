package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Balance;

public interface AccountDao {

    Balance getBalance(String user);
    Account getAccountByUserId(int userId);
    Account getAccountByAccId(int accountId);

    //model Account, void
    void updateAccount(Account accountToUpdate);

}
