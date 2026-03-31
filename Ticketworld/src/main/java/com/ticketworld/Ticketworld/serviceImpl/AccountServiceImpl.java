package com.ticketworld.Ticketworld.serviceImpl;

import com.ticketworld.Ticketworld.dto.AccountDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.repositories.AccountRepository;
import com.ticketworld.Ticketworld.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found: " + email));
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {

        //Encriptamos la contraseña
        accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));

        Account account = new Account(accountDTO);
        Account saved = accountRepository.save(account);
        return Account.toDTO(saved);
    }
}
