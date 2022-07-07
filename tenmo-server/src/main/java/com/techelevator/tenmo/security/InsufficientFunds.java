package com.techelevator.tenmo.security;

public class InsufficientFunds extends Exception {
    public InsufficientFunds() {
        super("Insufficient Funds.");
    }

}
