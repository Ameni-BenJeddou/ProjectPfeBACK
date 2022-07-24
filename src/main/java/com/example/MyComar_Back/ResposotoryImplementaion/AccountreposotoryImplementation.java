package com.example.MyComar_Back.ResposotoryImplementaion;

import com.example.MyComar_Back.Entities.Account;
import com.example.MyComar_Back.SpringSecurity.PasswordEncoder;
import com.example.MyComar_Back.SpringSecurity.jwt.UserDetailsImpl;
import com.example.MyComar_Back.reposotory.AccountReposotory;
import com.example.MyComar_Back.reposotoryInterface.AccountreposotoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountreposotoryImplementation implements AccountreposotoryInterface, UserDetailsService {
    private final AccountReposotory accountreposotory;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account=accountreposotory.findByemail(email);
        if (account==null)
        {
            throw new UsernameNotFoundException("Account email has not been found");
        }
        return UserDetailsImpl.build(account);
    }


    @Override
    public Account saveAccount(Account account) {
        return accountreposotory.save(account);
    }

    @Override
    public Account getAccount(String email) {
        return accountreposotory.findByemail(email);
    }

    @Override
    public List<Account> GetAccounts() {
        return accountreposotory.findAll();
    }

    @Override
    public Boolean existsByemail(String email) {
        return accountreposotory.existsByemail(email);
    }
    @Override
    public Optional<Account> FindById(Long Id) {
        return accountreposotory.findById(Id);
    }

    @Override
    public void updateAccount(Account a) {
        accountreposotory.save(a);
    }


}
