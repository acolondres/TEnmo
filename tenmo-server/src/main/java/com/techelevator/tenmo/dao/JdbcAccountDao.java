package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Balance;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component

public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Balance getBalance(String user) {

        String sql = "SELECT balance" +
                " From tenmo_account"+
                " JOIN tenmo_user ON tenmo_account.user_id = tenmo_user.user_id"+
                " WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,user);
                Balance balance = new Balance();
                if(results.next()){
                    String accountBalance = results.getString("balance");
                    balance.setBalance(new BigDecimal(accountBalance));
                }
                return balance;
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
