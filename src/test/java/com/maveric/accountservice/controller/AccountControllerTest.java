package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.BalanceDto;
import com.maveric.accountservice.dto.TransactionDto;
import com.maveric.accountservice.dto.UserDto;
import com.maveric.accountservice.feignconsumer.BalanceServiceConsumer;
import com.maveric.accountservice.feignconsumer.TransactionServiceConsumer;
import com.maveric.accountservice.feignconsumer.UserServiceConsumer;
import com.maveric.accountservice.service.AccountService;
import com.maveric.accountservice.service.AccountServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.maveric.accountservice.AccountServiceApplicationTests.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes=AccountController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(AccountController.class)
@Tag("Integeration Tests")
public class AccountControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private AccountService accountService;

    @MockBean
    BalanceServiceConsumer balanceServiceConsumer;

    @MockBean
    TransactionServiceConsumer transactionServiceConsumer;

    @MockBean
    UserServiceConsumer userServiceConsumer;

    @Mock
    ResponseEntity<BalanceDto> balanceDto;


    @Mock
    ResponseEntity<List<TransactionDto>> transactionDto;


    @Test
    public void shouldGetStatus200WhenRequestMadeTogetAccounts() throws Exception
    {
        mock.perform(get(apiV1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus201WhenRequestMadeToCreateAccounts() throws Exception
    {
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(getUserDto(), HttpStatus.OK);
        when(userServiceConsumer.getUserDetails(any(String.class))).thenReturn(responseEntity);
        mock.perform(post(apiV1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getAccountDto()))
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToGetAccountDetails() throws Exception
    {
        when(accountService.getAccountDetailsById(any(String.class))).thenReturn(getAccountDto());
        when(balanceServiceConsumer.getBalances(any(String.class))).thenReturn(balanceDto);
        when(balanceDto.getBody()).thenReturn(getBalanceDto());
        when(transactionServiceConsumer.getTransactionsByAccountId(any(String.class))).thenReturn(transactionDto);
        when(transactionDto.getBody()).thenReturn(Arrays.asList(getTransactionDto(),getTransactionDto()));

        mock.perform(get(apiV1+"/accountId1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToUpdateAccount() throws Exception
    {
        mock.perform(put(apiV1+"/accountId1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getAccountDto()))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToDeleteAccount() throws Exception
    {
        mock.perform(delete(apiV1+"/accountId1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
