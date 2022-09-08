package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.BalanceDto;
import com.maveric.accountservice.dto.TransactionDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.exception.PathParamsVsInputParamsMismatchException;
import com.maveric.accountservice.feignconsumer.BalanceServiceConsumer;
import com.maveric.accountservice.feignconsumer.TransactionServiceConsumer;
import com.maveric.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.maveric.accountservice.constants.Constants.BALANCE_NOT_FOUND_MESSAGE;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    BalanceServiceConsumer balanceServiceConsumer;
    @Autowired
    TransactionServiceConsumer transactionServiceConsumer;

    @GetMapping("customers/{customerId}/account")
    public ResponseEntity<List<AccountDto>> getAccounts(@PathVariable String customerId,@RequestParam(defaultValue = "0") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        List<AccountDto> accountDtoResponse = accountService.getAccounts(page,pageSize);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }

    @GetMapping("customers/{customerId}/accounts")
    public ResponseEntity<List<AccountDto>> getAccountByCustomerId(@PathVariable String customerId,@RequestParam(defaultValue = "0") Integer page,
                                                        @RequestParam(defaultValue = "5") Integer pageSize) {
        List<AccountDto> accountDtoResponse = accountService.getAccountByUserId(page,pageSize,customerId);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }

    @PostMapping("customers/{customerId}/accounts")
    public ResponseEntity<AccountDto> createAccount(@PathVariable String customerId, @Valid @RequestBody AccountDto accountDto) {
            AccountDto accountDtoResponse = accountService.createAccount(customerId,accountDto);
            return new ResponseEntity<>(accountDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccountDetails(@PathVariable String customerId,@PathVariable String accountId) {
        AccountDto accountDtoResponse = accountService.getAccountDetailsById(accountId);

        try {
                ResponseEntity<BalanceDto> balanceDto = balanceServiceConsumer.getBalances(accountId);
                accountDtoResponse.setBalance(balanceDto.getBody());
        }
        catch(Exception ex)
        {
            accountDtoResponse.setBalance(new BalanceDto());
            System.out.println("Exception has occured at Balance Service ->"+ex.getMessage());
        }


        try {
            ResponseEntity<List<TransactionDto>> transactionDto = transactionServiceConsumer.getTransactionsByAccountId(accountId);
            accountDtoResponse.setTransactions(transactionDto.getBody());
        }
        catch(Exception ex)
        {
            accountDtoResponse.setTransactions(new ArrayList<>());
            System.out.println("Exception has occured at Transaction Service ->"+ex.getMessage());
        }

        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }

    @PutMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String customerId,@PathVariable String accountId,@RequestBody AccountDto accountDto) {
                AccountDto accountDtoResponse = accountService.updateAccountDetails(customerId,accountId,accountDto);
                return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String customerId,@PathVariable String accountId) {
        String result = accountService.deleteAccount(accountId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
