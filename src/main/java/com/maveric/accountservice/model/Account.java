package com.maveric.accountservice.model;

import com.maveric.accountservice.constants.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "Account")
public class Account {

    @Id
    private String _id;
    private String customerId;
    private Type type;
    private String createdAt;
    private String updatedAt;
}
