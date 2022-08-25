package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;

import java.util.List;

public interface AccountService {

    public AccountDto createAccount(AccountDto transaction);
    public List<AccountDto> getAccounts(Integer page, Integer pageSize);
    public AccountDto getAccountDetailsById(String accountId);
    public AccountDto updateAccountDetails(String accountId,AccountDto accountDto);
    public String deleteAccount(String accountId);
}
