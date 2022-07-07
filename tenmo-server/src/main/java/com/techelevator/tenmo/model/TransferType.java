package com.techelevator.tenmo.model;

public class TransferType {

    private int transferTypeId;
    private String transferTypeInfo;

    public TransferType(int transferTypeId, String transferTypeInfo) {
        this.transferTypeId = transferTypeId;
        this.transferTypeInfo = transferTypeInfo;
    }

        public int getTransferTypeId () {
            return transferTypeId;
        }

        public void setTransferTypeId ( int transferTypeId){
            this.transferTypeId = transferTypeId;
        }

        public String getTransferTypeInfo () {
            return transferTypeInfo;
        }

        public void setTransferTypeInfo (String transferTypeInfo){
            this.transferTypeInfo = transferTypeInfo;
        }
    }








