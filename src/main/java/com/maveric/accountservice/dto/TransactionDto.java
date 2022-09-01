package com.maveric.accountservice.dto;

import com.maveric.accountservice.constants.TransactionType;
import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TransactionDto {

    private String _id;
    @NotNull(message = "Account Id is mandatory")
    private String accountId;
    @NotNull(message = "Type is mandatory - 'CREDIT' or 'DEBIT'")
    private TransactionType type;
    @NotNull(message = "Amount is mandatory")
    @Min(value=0,message = "Amount cannot be less than zero")
    private Number amount;
    private LocalDateTime createdAt;
}
