package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Balance;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao {
    @Override
    public Balance getBalance(String user) {
        return null;
    }

    @Override
    public Account getAccountByUserId(int userId) {
        return null;
    }

    @Override
    public Account getAccountByAccId(int accountId) {
        return null;
    }
}
