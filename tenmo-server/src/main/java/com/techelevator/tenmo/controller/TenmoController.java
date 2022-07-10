package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.model.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


@RestController

// Add preauthorize to require being authenticated to be able to access balance or transfer functions
@PreAuthorize("isAuthenticated()")
public class TenmoController {

    //Autowired happens right at start up of application
    //Spring then looks for class that matches the property to INJECT here easily.
    //We only have one each of these Dao beans, so no need to specify.
    @Autowired
    AccountDao accountDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TransferDao transferDao;
    @Autowired
    TransferTypeDao transferTypeDao;
    @Autowired
    TransferStatusDao transferStatusDAO;


    //Default path is localhost:8080
    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    //Use principal for current logged in user
    public Balance getBalance(Principal principal) {
        System.out.println(principal.getName());
        return accountDao.getBalance(principal.getName());
    }

    //Return(GET) a list of all users registered
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userDao.findAll();
    }


    //Create(POST) a new transfer with path /transfer/>new transfer id<
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.POST)
    //Throws exception for no funds, extends exception (Super).
    public void newTransfer(@RequestBody Transfer transfer, @PathVariable int id) throws InsufficientFundsException {
        //New BigDecimal of transfer amount
        BigDecimal amountOfTransfer = transfer.getAmount();
        //Use "Account" model + dao for new account sender/recipient
        Account accountFrom = accountDao.getAccountByAccId(transfer.getAccountFrom());
        Account accountInto = accountDao.getAccountByAccId(transfer.getAccountInto());
        //Use send and receive BigDecimal methods we created for the sender and recipient (these are found in our "Balance" model
        accountFrom.getBalance().sendMoney(amountOfTransfer);
        accountInto.getBalance().receiveMoney(amountOfTransfer);
        //New transfer
        transferDao.newTransfer(transfer);
        //Update account balances
        accountDao.updateAccount(accountFrom);
        accountDao.updateAccount(accountInto);
    }

    //Retrieve transfer TYPE (ex: REQUEST or SEND) by desc.
    @RequestMapping(path="/transfertype/filter", method = RequestMethod.GET)
    public TransferType getTransferTypeFromDescription(@RequestParam String description) {
        return transferTypeDao.getTransferTypeByInfo(description);
    }
    //Retrieve transfer STATUS (ex: Pending, Approved, Rejected) by desc.
    @RequestMapping(path="/transferstatus/filter", method = RequestMethod.GET)
    public TransferStatus getTransferStatusByDescription(@RequestParam String description) {
        return transferStatusDAO.getTransferStatusByInfo(description);
    }
    //Retrieve the account by specific user id
    @RequestMapping(path="/account/user/{id}", method = RequestMethod.GET)
    public Account getAccountByUserId(@PathVariable int id) {
        return accountDao.getAccountByUserId(id);
    }

    //Retrieve the account by specific account id
    @RequestMapping(path="/account/{id}", method = RequestMethod.GET)
    public Account getAccountFromAccountId(@PathVariable int id) {
        return accountDao.getAccountByAccId(id);
    }

    //List of all transfers specific to the user/>user id we pass<
    @RequestMapping(path="/transfers/user/{userId}", method = RequestMethod.GET)
    public List<Transfer> getTransfersByUserId(@PathVariable int userId) {
        return transferDao.getTransferUserId(userId);
    }

    //Retrieve transfer with specific transfer ID
    @RequestMapping(path="/transfers/{id}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable int id) {
        return transferDao.getTransferId(id);
    }

    //Retrieve a specific user based on user ID.
    @RequestMapping(path="/users/{id}", method = RequestMethod.GET)
    public User getUserByUserId(@PathVariable int id) {
        return userDao.getUserById(id);
    }

    //List all transfers
    @RequestMapping(path="/transfers", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers() {
        return transferDao.getAllTransfer();
    }


    //Get transfer type (ex: Request, Send) from transfer ID.
    @RequestMapping(path="/transfertype/{id}", method = RequestMethod.GET)
    public TransferType getTransferDescFromId(@PathVariable int id)  {
        return transferTypeDao.getTransferTypeFromId(id);
    }

    //Get transfer status (ex: Pending, Approved, Rejected) by the transfer ID.
    @RequestMapping(path="/transferstatus/{id}", method = RequestMethod.GET)
    public TransferStatus getTransferStatusFromId(@PathVariable int id) {
        return transferStatusDAO.getTransferStatusById(id);
    }

    //Update values with PUT for approved transfers
    @RequestMapping(path="/transfers/{id}", method = RequestMethod.PUT)
    public void updateTransferStatus(@RequestBody Transfer transfer, @PathVariable int id) throws InsufficientFundsException {

        if(transfer.getTransferStatusId() == transferStatusDAO.getTransferStatusByInfo("Approved").getTransferStatusId()) {

            BigDecimal amountToTransfer = transfer.getAmount();
            Account accountFrom = accountDao.getAccountByAccId(transfer.getAccountFrom());
            Account accountTo = accountDao.getAccountByAccId(transfer.getAccountInto());
            accountFrom.getBalance().sendMoney(amountToTransfer);
            accountTo.getBalance().receiveMoney(amountToTransfer);
            transferDao.updateTransfer(transfer);
            accountDao.updateAccount(accountFrom);
            accountDao.updateAccount(accountTo);
        } else {
            transferDao.updateTransfer(transfer);
        }

    }
}
