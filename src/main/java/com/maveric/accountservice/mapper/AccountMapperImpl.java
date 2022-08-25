package com.maveric.accountservice.mapper;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    public List<Account> mapToModel(List<AccountDto> dtoAccounts) {

        List<Account> list = new ArrayList<>(dtoAccounts.size());
        for (AccountDto accountDto : dtoAccounts) {
            list.add(map(accountDto));
        }
        return list;
    }

    @Override
    public List<AccountDto> mapToDto(List<Account> accounts) {

        List<AccountDto> list = new ArrayList<>(accounts.size());
        for (Account account : accounts) {
            list.add(map(account));
        }
        return list;
    }
}
