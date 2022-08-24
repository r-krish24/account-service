package com.maveric.accountservice.repository;

import com.maveric.accountservice.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account,String> {
}
