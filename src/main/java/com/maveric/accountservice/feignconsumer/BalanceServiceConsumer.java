package com.maveric.accountservice.feignconsumer;

import com.maveric.accountservice.dto.BalanceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="balance-service")
@Service
public interface BalanceServiceConsumer {
    @GetMapping("api/v1/accounts/{accountId}/balance")
    public ResponseEntity<BalanceDto> getBalance(@PathVariable String accountId);

    @GetMapping("api/v1/accounts/{accountId}/balances")
    public ResponseEntity <List<BalanceDto>> getBalances(@PathVariable String accountId);

}
