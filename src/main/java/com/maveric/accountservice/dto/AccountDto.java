package com.maveric.accountservice.dto;

import com.maveric.accountservice.constants.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountDto {
        private String _id;
        private String customerId;
        private Type type;
        private String createdAt;
        private String updatedAt;
}
