package com.maveric.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.maveric.accountservice.constants.AccountType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountDto {
        private String _id;
        @NotBlank(message = "Customer Id is mandatory")
        private String customerId;
        @NotNull(message = "Type is mandatory - 'SAVINGS' or 'CURRENT'")
        private AccountType type;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private BalanceDto balance;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<TransactionDto> transactions;
}
