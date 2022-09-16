package com.maveric.accountservice.feignconsumer;

import com.maveric.accountservice.dto.TransactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="transaction-service")
@Service
public interface TransactionServiceConsumer {

    @GetMapping("api/v1/accounts/{accountId}/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountId(@PathVariable String accountId);

    @DeleteMapping("api/v1/accounts/{accountId}/transactions")
    public ResponseEntity<String> deleteTransactionByAccountId(@PathVariable String accountId);
}
