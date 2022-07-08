package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    void newTransfer(Transfer transfer);

    List<Transfer> getTransferUserId(int userId);
    Transfer getTransferId(int transferId);
    List<Transfer> getAllTransfer();
    void updateTransfer(Transfer transfer);


}
