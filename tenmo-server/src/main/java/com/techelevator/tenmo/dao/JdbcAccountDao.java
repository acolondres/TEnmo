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
        String sql = " Select account_id,user_id,balance"+
                " FROM tenmo_account" +
                " WHERE user_id =?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
        Account account = null;
        if(results.next()){
            account = mapResultsToAccount(results);
        }return account;

    }

    @Override
    public Account getAccountByAccId(int accountId) {

        String sql = " Select account_id, user_id, balance "+
                " FROM tenmo_account"+
                " WHERE account_id = ? ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,accountId);
        Account account = null;
        if(results.next()){
            account = mapResultsToAccount(results);
        }return account;
    }

    @Override
    public void updateAccount(Account accountToUpdate) {
        String sql = "UPDATE tenmo_account " +
                " SET balance = ? " +
                " WHERE account_id = ? ";
        jdbcTemplate.update(sql, accountToUpdate.getBalance().getBalance(), accountToUpdate.getAccountId());
    }


    private Account mapResultsToAccount(SqlRowSet results) {
    int accountId = results.getInt("account_id");
    int userId = results.getInt("user_id");
    Balance balance = new Balance();
    String accountBalance = results.getString("balance");
    balance.setBalance(new BigDecimal(accountBalance));
    return new Account(accountId, userId, balance);
}

}
