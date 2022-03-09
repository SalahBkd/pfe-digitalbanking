package org.ybs.accountcommandsside.aggregates;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.ybs.accountcommandsside.repositories.CustomerRepo;
import org.ybs.coreapi.commands.*;
import org.ybs.coreapi.enums.AccountStatus;
import org.ybs.coreapi.events.*;
import org.ybs.coreapi.exceptions.CustomerDoesntExistException;
import org.ybs.coreapi.exceptions.InsufficientBalanceException;
import org.ybs.coreapi.exceptions.NegativeAmountException;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountID;
    private double balance;
    private String currency;
    private AccountStatus status;
    private String customerID;
    @Autowired
    private CustomerRepo customerRepo;

    // Needed By Axon
    public AccountAggregate() {
    }

    // CREATE
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        // TODO business logic
        log.info("CreateAccountCommand received");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getBalance(),
                command.getCurrency(),
                AccountStatus.CREATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("AccountCreatedEvent occurred");
        this.accountID = event.getId();
        this.balance = event.getBalance();
        this.currency = event.getCurrency();
        this.status = event.getStatus();

        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED
        ));
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event) {
        this.status = event.getStatus();
    }

    // CREDIT
    @CommandHandler
    public void handle(CreditAccountCommand creditAccountCommand) {
        log.info("CreditAccountCommand received");
        if(creditAccountCommand.getAmount() < 0) throw new NegativeAmountException("amount should not be lesser than 0");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                creditAccountCommand.getId(),
                creditAccountCommand.getAmount(),
                creditAccountCommand.getCurrency(),
                creditAccountCommand.getDate()
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent accountCreditedEvent) {
        log.info("CreditAccountCommand occurred");
        this.balance += accountCreditedEvent.getAmount();
    }


    // DEBIT
    @CommandHandler
    public void handle(DebitAccountCommand debitAccountCommand) {
        log.info("DebitAccountCommand received");
        if(debitAccountCommand.getAmount() < 0) throw new NegativeAmountException("Amount should not be lesser than 0");
        if(this.balance < debitAccountCommand.getAmount()) throw new InsufficientBalanceException("Insufficient Balance: " + balance + ", the amount is greater than the current account balance.");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                debitAccountCommand.getId(),
                debitAccountCommand.getAmount(),
                debitAccountCommand.getCurrency(),
                debitAccountCommand.getDate()
        ));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent accountDebitedEvent) {
        this.balance -= accountDebitedEvent.getAmount();
    }

    // DELETE
    @CommandHandler
    public void handle(DeleteAccountCommand deleteAccountCommand) {
        log.info("DeleteAccountCommand received");
        // TODO business logic
        AggregateLifecycle.apply(new AccountDeletedEvent(
                deleteAccountCommand.getId()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerDeletedEvent event) {
        this.accountID = event.getId();
    }

    // LINK
    @CommandHandler
    public void handle(LinkAccountCommand linkAccountCommand) {
        log.info("LinkAccountCommand received");
        boolean isCustomerExists = customerRepo.existsById(linkAccountCommand.getCustomerID());
        if(!isCustomerExists) throw new CustomerDoesntExistException("Customer Doesn't Exist !");

        AggregateLifecycle.apply(new AccountLinkedEvent(
                linkAccountCommand.getId(),
                linkAccountCommand.getCustomerID()
        ));
    }

    @EventSourcingHandler
    public void on(AccountLinkedEvent accountDebitedEvent) {
        this.customerID = accountDebitedEvent.getCustomerId();
    }

}
