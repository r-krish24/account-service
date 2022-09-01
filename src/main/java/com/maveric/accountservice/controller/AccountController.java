package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.BalanceDto;
import com.maveric.accountservice.dto.TransactionDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.feignconsumer.BalanceServiceConsumer;
import com.maveric.accountservice.feignconsumer.TransactionServiceConsumer;
import com.maveric.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("customers/{customerId}/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts(@PathVariable String customerId,@RequestParam(defaultValue = "0") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        List<AccountDto> accountDtoResponse = accountService.getAccounts(page,pageSize);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }

    @PostMapping("customers/{customerId}/accounts")
    public ResponseEntity<AccountDto> createAccount(@PathVariable String customerId, @Valid @RequestBody AccountDto accountDto) {
        AccountDto accountDtoResponse = accountService.createAccount(accountDto);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccountDetails(@PathVariable String customerId,@PathVariable String accountId) {
        AccountDto accountDtoResponse = accountService.getAccountDetailsById(accountId);

        ResponseEntity<BalanceDto> balanceDto = balanceServiceConsumer.getBalance(accountId);
        try {
                accountDtoResponse.setBalance(balanceDto.getBody());
        }
        catch(AccountNotFoundException ex)
        {
            throw new AccountNotFoundException(BALANCE_NOT_FOUND_MESSAGE+accountId);
        }

        /*ResponseEntity<List<TransactionDto>> transactionDto = transactionServiceConsumer.getTransactionsByAccountId(accountId);
        try {
            accountDtoResponse.setTransactions(transactionDto.getBody());
        }
        catch(AccountNotFoundException ex)
        {
            throw new AccountNotFoundException(BALANCE_NOT_FOUND_MESSAGE+accountId);
        } */

        /*ResponseEntity<List<BalanceDto>> balanceDto = balanceServiceConsumer.getBalances(accountId);
        System.out.println("Resp->"+balanceDto);
        try {
            System.out.println("Account ID->" + balanceDto.getBody().get(0).getAccountId());
            accountDtoResponse.setBalance(balanceDto.getBody().get(0));
        }
        catch(AccountNotFoundException ex)
        {
            throw new AccountNotFoundException(BALANCE_NOT_FOUND_MESSAGE+accountId);
        } */
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }

    @PutMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String customerId,@PathVariable String accountId,@RequestBody AccountDto accountDto) {
        AccountDto accountDtoResponse = accountService.updateAccountDetails(accountId,accountDto);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String customerId,@PathVariable String accountId) {
        String result = accountService.deleteAccount(accountId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
