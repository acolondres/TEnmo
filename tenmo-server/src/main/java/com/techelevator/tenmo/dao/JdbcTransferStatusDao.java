package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatus;
import org.springframework.stereotype.Component;

@Component
public class JdbcTransferStatusDao implements TransferStatusDao {
    @Override
    public TransferStatus getTransferStatusById(int transferStatusId) {
        return null;
    }

    @Override
    public TransferStatus getTransferStatusByInfo(String info) {
        return null;
    }
}
