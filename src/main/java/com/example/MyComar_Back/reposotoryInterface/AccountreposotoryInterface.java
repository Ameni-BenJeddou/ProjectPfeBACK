package com.example.MyComar_Back.reposotoryInterface;

import com.example.MyComar_Back.Entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountreposotoryInterface  {
    Account saveAccount( Account account);
    Account getAccount(String email);
    List<Account> GetAccounts();
    Boolean existsByemail(String email);
    Optional<Account> FindById(Long Id);
    void updateAccount( Account a);


}
