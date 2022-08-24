package com.maveric.accountservice.mapper;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.model.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="Account")
public interface AccountMapper {
    Account map(AccountDto accountDto);

    AccountDto map(Account account);

    List<Account> map (List<AccountDto> accounts);
}
