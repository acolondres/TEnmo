package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Balance;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
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






}
