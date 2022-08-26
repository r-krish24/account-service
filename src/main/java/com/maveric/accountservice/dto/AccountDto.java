package com.maveric.accountservice.dto;

import com.maveric.accountservice.constants.Type;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountDto {
        private String _id;
        private String customerId;
        private Type type;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
}
