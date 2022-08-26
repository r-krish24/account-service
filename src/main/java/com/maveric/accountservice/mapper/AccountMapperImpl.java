package com.maveric.accountservice.mapper;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapperImpl implements AccountMapper{
    @Override
    public Account map(AccountDto accountDto) {
        return new Account(
                accountDto.get_id(),
                accountDto.getCustomerId(),
                accountDto.getType(),
                accountDto.getCreatedAt(),
                accountDto.getUpdatedAt()
                );
    }

    @Override
    public AccountDto map(Account account) {
        return new AccountDto(
                account.get_id(),
                account.getCustomerId(),
                account.getType(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

    @Override
    public List<Account> mapToModel(List<AccountDto> accountsDto) {
        return accountsDto.stream().map(accountDto -> new Account(
                accountDto.get_id(),
                accountDto.getCustomerId(),
                accountDto.getType(),
                accountDto.getCreatedAt(),
                accountDto.getUpdatedAt()
        )).collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> mapToDto(List<Account> accounts) {
        return accounts.stream().map(account -> new AccountDto(
                account.get_id(),
                account.getCustomerId(),
                account.getType(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        )).collect(Collectors.toList());
    }
}
