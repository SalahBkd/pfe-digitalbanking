package org.ybs.accountqueryside.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ybs.accountqueryside.entities.Account;
import org.ybs.coreapi.queries.GetAccountByIdQuery;
import org.ybs.coreapi.queries.GetAllAccountsQuery;
import org.ybs.coreapi.queries.GetAllCustomersQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/accounts/query")
@AllArgsConstructor
@Slf4j
public class AccountQueryController {
    private QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<Account>> getAccounts() {
        return queryGateway.query(new GetAllAccountsQuery(),
                ResponseTypes.multipleInstancesOf(Account.class));
    }

    @GetMapping("/byId/{id}")
    public Account getAccount(@PathVariable String id) {
        return queryGateway.query(new GetAccountByIdQuery(id), ResponseTypes.instanceOf(Account.class)).join();
    }
}

