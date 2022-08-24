package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("customer/{customerId}/accounts")
    public ResponseEntity<AccountDto> createTransaction(@PathVariable String customerId, @RequestBody AccountDto accountDto) {
        AccountDto accountDtoResponse = accountService.createAccount(accountDto);
        return new ResponseEntity<AccountDto>(accountDtoResponse, HttpStatus.OK);
    }
}
