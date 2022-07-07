package com.techelevator.tenmo.model;

public class TransferStatus {

    private int transferStatusId;
    private String transferStatusInfo;

    public TransferStatus(int transferStatusId, String transferStatusInfo) {
        this.transferStatusId = transferStatusId;
        this.transferStatusInfo = transferStatusInfo;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public String getTransferStatusInfo() {
        return transferStatusInfo;
    }

    public void setTransferStatusInfo(String transferStatusInfo) {
        this.transferStatusInfo = transferStatusInfo;
    }


}
