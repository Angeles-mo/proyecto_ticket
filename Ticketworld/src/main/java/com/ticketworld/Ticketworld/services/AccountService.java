package com.ticketworld.Ticketworld.services;

import com.ticketworld.Ticketworld.dto.AccountDTO;
import com.ticketworld.Ticketworld.entity.Account;

public interface AccountService {
    Account findByEmail(String email);
    AccountDTO save(AccountDTO accountDTO);
}
