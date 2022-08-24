package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.model.Account;

import java.util.List;

public interface AccountService {

    public AccountDto createAccount(AccountDto transaction);
    public List<AccountDto> getAccounts(String customerId, Integer page, Integer pageSize);
    public AccountDto getAccountDetailsById(String customerId,String accountId);
}
