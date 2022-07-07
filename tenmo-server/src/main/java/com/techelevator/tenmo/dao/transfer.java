package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface transfer {
    void newTransfer(Transfer transfer);

    List<Transfer> getTransferUserId(int userId);
    Transfer getTransferId(int transferId);
    List<Transfer> getAllTransfer();

}
