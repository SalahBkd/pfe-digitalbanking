package org.ybs.customercommandsside.aggregates;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.ybs.coreapi.commands.CreateCustomerCommand;
import org.ybs.coreapi.commands.DeleteCustomerCommand;
import org.ybs.coreapi.commands.UpdateCustomerCommand;
import org.ybs.coreapi.events.CustomerCreatedEvent;
import org.ybs.coreapi.events.CustomerDeletedEvent;
import org.ybs.coreapi.events.CustomerUpdatedEvent;

@Aggregate
@Slf4j
public class CustomerAggregate {
    @AggregateIdentifier
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public CustomerAggregate() {
    }

    // CREATE
    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        log.info("CreateCustomerCommand received");
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.getId(),
                command.getName(),
                command.getEmail(),
                command.getPhoneNumber(),
                command.getAddress()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event){
        log.info("CustomerCreatedEvent occured");
        this.customerId = event.getId();
        this.name = event.getName();
        this.email = event.getEmail();
        this.phoneNumber = event.getPhoneNumber();
        this.address = event.getAddress();
    }

    // UPDATE
    @CommandHandler
    public void handle(UpdateCustomerCommand updateCustomerCommand) {
        // TODO business logic
        AggregateLifecycle.apply(new CustomerUpdatedEvent(
                updateCustomerCommand.getId(),
                updateCustomerCommand.getName(),
                updateCustomerCommand.getEmail(),
                updateCustomerCommand.getPhoneNumber(),
                updateCustomerCommand.getAddress()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerUpdatedEvent event) {
        this.name = event.getName();
        this.email = event.getEmail();
        this.phoneNumber = event.getPhoneNumber();
        this.address = event.getAddress();
    }

    // DELETE
    @CommandHandler
    public void handle(DeleteCustomerCommand deleteCustomerCommand) {
        // TODO business logic
        AggregateLifecycle.apply(new CustomerDeletedEvent(
                deleteCustomerCommand.getId()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerDeletedEvent event) {
        this.customerId = event.getId();
    }
}
