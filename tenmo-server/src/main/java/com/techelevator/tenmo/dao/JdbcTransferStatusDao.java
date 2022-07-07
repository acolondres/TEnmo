package com.techelevator.tenmo.dao;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.tenmo.model.TransferStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTransferStatusDao implements TransferStatusDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcTransferStatusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TransferStatus getTransferStatusById(int transferStatusId) {
        String sql = " SELECT transfer_status_id, transfer_status_desc"+
                " FROM transfer_status"+
                " WHERE transfer_status_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,transferStatusId);
        TransferStatus transferStatus = null;
        if(results.next()){
            int transferStatusId2 = results.getInt("transfer_status_id");
            String transferStatusInfo = results.getString("transfer_status_desc");
            transferStatus = new TransferStatus(transferStatusId2, transferStatusInfo);
        }return transferStatus;
    }

    @Override
    public TransferStatus getTransferStatusByInfo(String info) {
        String sql = " SELECT transfer_status_id, transfer_status_desc"+
                " FROM transfer_status"+
                " WHERE transfer_status_desc = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,info);
        TransferStatus transferStatus = null;
        if(results.next()){
            int transferStatusId = results.getInt("transfer_status_id");
            String transferStatusInfo = results.getString("transfer_status_desc");
            transferStatus = new TransferStatus(transferStatusId, transferStatusInfo);
        }return transferStatus;
    }
}
