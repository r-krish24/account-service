package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.mapper.AccountMapper;
import com.maveric.accountservice.model.Account;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.maveric.accountservice.constants.Constants.getCurrentDateTime;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountMapper mapper;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        //Adding Time
        accountDto.setCreatedAt(getCurrentDateTime());
        accountDto.setUpdatedAt(getCurrentDateTime());

        Account account = mapper.map(accountDto);
        Account transactionResult = repository.save(account);
        return  mapper.map(transactionResult);
    }
}
