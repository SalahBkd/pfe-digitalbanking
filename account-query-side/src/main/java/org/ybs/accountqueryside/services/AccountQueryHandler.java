package org.ybs.accountqueryside.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.ybs.accountqueryside.entities.Account;
import org.ybs.accountqueryside.repositories.AccountRepo;
import org.ybs.coreapi.queries.GetAccountByIdQuery;
import org.ybs.coreapi.queries.GetAllAccountsQuery;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountQueryHandler {
    private AccountRepo accountRepo;

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery accountsQuery) {
        return accountRepo.findAll();
    }

    @QueryHandler
    public Account on(GetAccountByIdQuery getAccountQuery) {
        return accountRepo.findById(getAccountQuery.getId()).get();
    }
}
