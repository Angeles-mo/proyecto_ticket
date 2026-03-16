package com.ticketworld.Ticketworld.jwt;


import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetails implements UserDetailsService {

    @Autowired
    private final AccountRepository accountRepository;

    public JwtUserDetails(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found: " + email));
        return new User(
                account.getEmail(),
                account.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + account.getRole().name())));
    }
}
