package com.maveric.accountservice.service;

import com.maveric.accountservice.constants.AccountType;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.UserDto;
import com.maveric.accountservice.mapper.AccountMapperImpl;
import com.maveric.accountservice.model.Account;
import com.maveric.accountservice.repository.AccountRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.maveric.accountservice.AccountServiceApplicationTests.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl service;

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountMapperImpl mapper;

    @Mock
    private Page pageResult;


    @Test
    public void testCreateAccount() throws Exception{
        when(mapper.map(any(AccountDto.class))).thenReturn(getAccount());
        when(mapper.map(any(Account.class))).thenReturn(getAccountDto());
        when(repository.save(any())).thenReturn(getAccount());

        AccountDto transactionDto = service.createAccount("1234",getAccountDto());

        assertSame(transactionDto.getCustomerId(), getAccount().getCustomerId());
    }

    @Test
    public void testGetAccounts() {
        Page<Account> pagedResponse = new PageImpl(Arrays.asList(getAccount(),getAccount()));
        when(repository.findAll(any(Pageable.class))).thenReturn(pagedResponse);
        when(pageResult.hasContent()).thenReturn(true);
        when(pageResult.getContent()).thenReturn(Arrays.asList(getAccount(),getAccount()));
        when(mapper.mapToDto(any())).thenReturn(Arrays.asList(getAccountDto(),getAccountDto()));

        List<AccountDto> transactions = service.getAccounts(1,1);

        assertEquals("1234", transactions.get(0).getCustomerId());
        assertEquals(AccountType.SAVINGS, transactions.get(1).getType());
    }

    @Test
    public void testGetAccountByUserId() {
        Page<Account> pagedResponse = new PageImpl(Arrays.asList(getAccount(),getAccount()));
        when(repository.findByCustomerId(any(Pageable.class),eq("1234"))).thenReturn(pagedResponse);
        when(pageResult.hasContent()).thenReturn(true);
        when(pageResult.getContent()).thenReturn(Arrays.asList(getAccount(),getAccount()));
        when(mapper.mapToDto(any())).thenReturn(Arrays.asList(getAccountDto(),getAccountDto()));

        List<AccountDto> transactions = service.getAccountByUserId(1,1,"1234");

        assertEquals("1234", transactions.get(0).getCustomerId());
        assertEquals(AccountType.SAVINGS, transactions.get(1).getType());
    }

    @Test
    public void testGetAccountById() {
        when(repository.findById("123")).thenReturn(Optional.of(getAccount()));
        when(mapper.map(any(Account.class))).thenReturn(getAccountDto());

        AccountDto transactionDto = service.getAccountDetailsById("123");

        assertSame(transactionDto.getType(),getAccountDto().getType());
    }

    @Test
    public void testUpdateAccountById() {
        when(repository.findById("123")).thenReturn(Optional.of(getAccount()));
        when(repository.save(any())).thenReturn(getAccount());
        when(mapper.map(any(Account.class))).thenReturn(getAccountDto());

        AccountDto accountDto = service.updateAccountDetails("1234","123",getAccountDto());

        assertSame(accountDto.getType(),getAccountDto().getType());
    }

    @Test
    public void testDeleteAccountById() {

        when(repository.findById("123")).thenReturn(Optional.of(getAccount()));
        willDoNothing().given(repository).deleteById("123");

        String transactionDto = service.deleteAccount("123");

        assertSame( "Account deleted successfully.",transactionDto);
    }


}
