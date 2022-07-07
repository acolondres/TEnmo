package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcTransferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void newTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                " VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, transfer.getTransferId(), transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFrom(), transfer.getAmount(), transfer.getAccountInto());
        
    }

    @Override
    public List<Transfer> getTransferUserId(int userId) {
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer " +
                "WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        List<Transfer> transfers = new ArrayList<>();
        while(results.next()){
            transfers.add(mapResultsToTransfer(results));
        }return transfers;

    }


    @Override
    public Transfer getTransferId(int transferId) {
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer " +
                "WHERE transfer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        Transfer transfer = null;
        if(results.next()){
           transfer = mapResultsToTransfer(results);
        } return transfer;

    }

    @Override
    public List<Transfer> getAllTransfer() {
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Transfer> transfers = new ArrayList<>();
        while(results.next()){
            transfers.add(mapResultsToTransfer(results));
        }return transfers;

    }




    private Transfer mapResultsToTransfer(SqlRowSet results) {
        int transferId = results.getInt("transfer_id");
        int transferTypeId = results.getInt("transfer_type_id");
        int transferStatusId = results.getInt("transfer_status_id");
        int accountFrom = results.getInt("account_from");
        int accountInto = results.getInt("account_to");
        String amount2 = results.getString("amount");

       Transfer transfer = new Transfer(accountFrom, accountInto, new BigDecimal(amount2), transferStatusId, transferTypeId, transferId);
       return transfer;
    }


}
