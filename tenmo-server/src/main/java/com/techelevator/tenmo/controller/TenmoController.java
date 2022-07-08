package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.security.InsufficientFunds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


@RestController
@PreAuthorize("isAuthenticated()")
public class TenmoController {

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


    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public Balance getBalance(Principal principal) {
        System.out.println(principal.getName());
        return accountDao.getBalance(principal.getName());
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userDao.findAll();
    }



    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.POST)
    public void newTransfer(@RequestBody Transfer transfer, @PathVariable int id) throws InsufficientFunds {
        BigDecimal amountOfTransfer = transfer.getAmount();
        Account accountFrom = accountDao.getAccountByAccId(transfer.getAccountFrom());
        Account accountInto = accountDao.getAccountByAccId(transfer.getAccountInto());
        accountFrom.getBalance().sendMoney(amountOfTransfer);
        accountInto.getBalance().receiveMoney(amountOfTransfer);
        transferDao.newTransfer(transfer);
        accountDao.updateAccount(accountFrom);
        accountDao.updateAccount(accountInto);
    }

    @RequestMapping(path="/transfertype/filter", method = RequestMethod.GET)
    public TransferType getTransferTypeFromDescription(@RequestParam String description) {
        return transferTypeDao.getTransferTypeByInfo(description);
    }

    @RequestMapping(path="/transferstatus/filter", method = RequestMethod.GET)
    public TransferStatus getTransferStatusByDescription(@RequestParam String description) {
        return transferStatusDAO.getTransferStatusByInfo(description);
    }

    @RequestMapping(path="/account/user/{id}", method = RequestMethod.GET)
    public Account getAccountByUserId(@PathVariable int id) {
        return accountDao.getAccountByUserId(id);
    }

    @RequestMapping(path="/account/{id}", method = RequestMethod.GET)
    public Account getAccountFromAccountId(@PathVariable int id) {
        return accountDao.getAccountByAccId(id);
    }

    @RequestMapping(path="/transfers/user/{userId}", method = RequestMethod.GET)
    public List<Transfer> getTransfersByUserId(@PathVariable int userId) {
        return transferDao.getTransferUserId(userId);
    }

    @RequestMapping(path="/transfers/{id}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable int id) {
        return transferDao.getTransferId(id);
    }

    @RequestMapping(path="/users/{id}", method = RequestMethod.GET)
    public User getUserByUserId(@PathVariable int id) {
        return userDao.getUserById(id);
    }

    @RequestMapping(path="/transfers", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers() {
        return transferDao.getAllTransfer();
    }

    @RequestMapping(path="/transfertype/{id}", method = RequestMethod.GET)
    public TransferType getTransferDescFromId(@PathVariable int id)  {
        return transferTypeDao.getTransferTypeFromId(id);
    }
    @RequestMapping(path="/transferstatus/{id}", method = RequestMethod.GET)
    public TransferStatus getTransferStatusFromId(@PathVariable int id) {
        return transferStatusDAO.getTransferStatusById(id);
    }


    @RequestMapping(path="/transfers/{id}", method = RequestMethod.PUT)
    public void updateTransferStatus(@RequestBody Transfer transfer, @PathVariable int id) throws InsufficientFunds {

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
