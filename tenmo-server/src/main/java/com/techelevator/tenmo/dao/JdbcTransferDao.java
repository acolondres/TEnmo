package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class JdbcTransferDao implements transfer {
    @Override
    public void newTransfer(Transfer transfer) {
        
    }

    @Override
    public List<Transfer> getTransferUserId(int userId) {
        return null;
    }

    @Override
    public Transfer getTransferId(int transferId) {
        return null;
    }

    @Override
    public List<Transfer> getAllTransfer() {
        return null;
    }
}
