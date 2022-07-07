package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@Component
public class JdbcTransferTypeDao implements TransferTypeDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcTransferTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TransferType getTransferTypeFromId(int transferId) {
        String sql = " SELECT transfer_type_id, transfer_type_desc"+
                " FROM transfer_type"+
                " WHERE transfer_type_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,transferId);
        TransferType transferType = null;
        if(results.next()){
            int transferTypeId = results.getInt("transfer_type_id");
            String transferTypeInfo = results.getString("transfer_type_desc");
            transferType = new TransferType(transferTypeId,transferTypeInfo);
        }

        return transferType;
    }

    @Override
    public TransferType getTransferTypeByInfo(String info) {
        String sql = " SELECT transfer_type_id, transfer_type_desc"+
                " FROM transfer_type"+
                " WHERE transfer_type_desc = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,info);
        TransferType transferType = null;
        if(results.next()){
            int transferTypeId = results.getInt("transfer_type_id");
            String transferTypeInfo = results.getString("transfer_type_desc");
            transferType = new TransferType(transferTypeId,transferTypeInfo);
        }

        return transferType;
    }
}
