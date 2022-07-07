package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatus;

public interface TransferStatusDao {
    TransferStatus getTransferStatusById(int transferStatusId);
    TransferStatus getTransferStatusByInfo(String info);


}
