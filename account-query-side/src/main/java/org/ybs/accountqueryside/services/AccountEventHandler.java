package org.ybs.accountqueryside.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import org.ybs.accountqueryside.entities.Account;
import org.ybs.accountqueryside.entities.Operation;
import org.ybs.accountqueryside.repositories.AccountRepo;
import org.ybs.accountqueryside.repositories.OperationRepo;
import org.ybs.coreapi.enums.OperationType;
import org.ybs.coreapi.events.*;

@Service
@AllArgsConstructor
@Slf4j
public class AccountEventHandler {
    private AccountRepo accountRepo;
    private OperationRepo operationRepo;

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        log.info("========================");
        log.info("AccountCreatedEvent received");
        Account account = new Account();
        account.setId(accountCreatedEvent.getId());
        account.setBalance(accountCreatedEvent.getBalance());
        account.setAccountStatus(accountCreatedEvent.getStatus());
        account.setCurrency(accountCreatedEvent.getCurrency());
        account.setOperations(null);
        accountRepo.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent accountActivatedEvent) {
        log.info("========================");
        log.info("AccountActivatedEvent received");
        Account account = accountRepo.findById(accountActivatedEvent.getId()).get();
        account.setAccountStatus(accountActivatedEvent.getStatus());
        accountRepo.save(account);
    }

    @EventHandler
    public void on(AccountCreditedEvent accountCreditedEvent) {
        log.info("========================");
        log.info("AccountCreditedEvent received");
        Account account = accountRepo.findById(accountCreditedEvent.getId()).get();
        Operation operation = new Operation();

        operation.setAmount(accountCreditedEvent.getAmount());
        operation.setDate(accountCreditedEvent.getDate());
        operation.setOperationType(OperationType.CREDIT);
        operation.setAccount(account);
        operationRepo.save(operation);

        account.setBalance(account.getBalance() + accountCreditedEvent.getAmount());
        accountRepo.save(account);
    }

    @EventHandler
    public void on(AccountDebitedEvent accountDebitedEvent) {
        log.info("========================");
        log.info("AccountDebitedEvent received");
        Account account = accountRepo.findById(accountDebitedEvent.getId()).get();
        Operation operation = new Operation();

        operation.setAmount(accountDebitedEvent.getAmount());
        operation.setDate(accountDebitedEvent.getDate());
        operation.setOperationType(OperationType.DEBIT);
        operation.setAccount(account);
        operationRepo.save(operation);

        account.setBalance(account.getBalance() - accountDebitedEvent.getAmount());
        accountRepo.save(account);
    }

    @EventHandler
    public void on(AccountDeletedEvent event) {
        log.info("========================");
        log.info("AccountDeletedEvent received");
        Account customer = accountRepo.findById(event.getId()).get();
        accountRepo.deleteById(customer.getId());
    }

    @EventHandler
    public void on(AccountLinkedEvent accountLinkedEvent) {
        log.info("========================");
        log.info("AccountLinkedEvent received");
        Account account = accountRepo.findById(accountLinkedEvent.getId()).get();
        account.setCustomerID(accountLinkedEvent.getCustomerId());
        accountRepo.save(account);
    }
}
