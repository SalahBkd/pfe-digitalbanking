package org.ybs.accountcommandsside.aggregates;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.ybs.coreapi.commands.*;
import org.ybs.coreapi.enums.AccountStatus;
import org.ybs.coreapi.events.*;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountID;
    private double balance;
    private String currency;
    private AccountStatus status;

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
        // TODO if(creditAccountCommand.getAmount() < 0) throw new NegativeAmountException("amount should not be lesser than 0");
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


    // DEBIT ACCOUNT
    @CommandHandler // when the command is emitted in the command bus this method gets executed
    public void handle(DebitAccountCommand debitAccountCommand) {
        // TODO if(debitAccountCommand.getAmount() < 0) throw new NegativeAmountException("Amount should not be lesser than 0");
        // TODO if(this.balance < debitAccountCommand.getAmount()) throw new InsufficientBalanceException("Insufficient Balance: " + balance + ", the amount is greater than the current account balance.");
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


}
