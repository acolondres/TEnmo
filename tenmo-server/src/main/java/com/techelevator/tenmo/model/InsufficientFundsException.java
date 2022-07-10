package com.techelevator.tenmo.model;

//Make it extend the parent exception. Might be a vague message.
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Insufficient Funds.");
    }

}
