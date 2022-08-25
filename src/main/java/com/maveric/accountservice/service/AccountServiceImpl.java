package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.mapper.AccountMapper;
import com.maveric.accountservice.model.Account;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.maveric.accountservice.constants.Constants.getCurrentDateTime;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountMapper mapper;

    @Override
    public List<AccountDto> getAccounts(Integer page, Integer pageSize) {
        Pageable paging = PageRequest.of(page, pageSize);
        Page<Account> pageResult = repository.findAll(paging);
        if(pageResult.hasContent()) {
            List<Account> account = pageResult.getContent();
            return mapper.mapToDto(account);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        //Adding Time
        accountDto.setCreatedAt(getCurrentDateTime());
        accountDto.setUpdatedAt(getCurrentDateTime());

        Account account = mapper.map(accountDto);
        Account accountResult = repository.save(account);
        return  mapper.map(accountResult);
    }

    @Override
    public AccountDto getAccountDetailsById(String accountId) {
        Account accountResult=repository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return mapper.map(accountResult);
    }

    @Override
    public AccountDto updateAccountDetails(String accountId, AccountDto accountDto) {
        Account accountResult=repository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        accountResult.set_id(accountResult.get_id());
        accountResult.setCustomerId(accountDto.getCustomerId());
        accountResult.setType(accountDto.getType());
        accountResult.setCreatedAt(accountResult.getCreatedAt());
        accountResult.setUpdatedAt(getCurrentDateTime());
        Account accountUpdated = repository.save(accountResult);
        return mapper.map(accountUpdated);
    }

    @Override
    public String deleteAccount(String accountId) {
        repository.deleteById(accountId);
        return "Account deleted successfully.";
    }


}
