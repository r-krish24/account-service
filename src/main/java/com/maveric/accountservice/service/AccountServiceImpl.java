package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
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
import java.util.stream.Collectors;

import static com.maveric.accountservice.constants.Constants.getCurrentDateTime;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountMapper mapper;

    @Override
    public List<AccountDto> getAccounts(String customerId,Integer page, Integer pageSize) {
        Pageable paging = (Pageable) PageRequest.of(page, pageSize);
        Page<Account> pageResult = repository.findAll(paging);
        if(pageResult.hasContent()) {
            return pageResult.getContent().stream()
                    .map(
                            account -> mapper.map(account)
                    ).collect(
                            Collectors.toList()
                    );
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
        Account transactionResult = repository.save(account);
        return  mapper.map(transactionResult);
    }


}
