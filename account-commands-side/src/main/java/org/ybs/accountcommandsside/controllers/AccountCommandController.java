package org.ybs.accountcommandsside.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;
import org.ybs.coreapi.commands.*;
import org.ybs.coreapi.dtos.*;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/accounts/command")
@Slf4j
@AllArgsConstructor
public class AccountCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;


    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.getInitialBalance(),
                request.getCurrency()
        ));
        return response;
    }

    @PutMapping(path = "/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO requestDTO) {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreditAccountCommand(
                requestDTO.getAccountID(),
                requestDTO.getAmount(),
                requestDTO.getCurrency(),
                new Date()
        ));
        return commandResponse;
    }

    @PutMapping(path = "/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO requestDTO) {
        CompletableFuture<String> commandResponse = commandGateway.send(new DebitAccountCommand(
                requestDTO.getAccountID(),
                requestDTO.getAmount(),
                requestDTO.getCurrency(),
                new Date()
        ));
        return commandResponse;
    }

    @DeleteMapping(path = "/delete")
    public CompletableFuture<String> deleteAccount(@RequestBody DeleteAccountRequestDTO requestDTO) {
        return commandGateway.send(new DeleteAccountCommand(
                requestDTO.getAccountID()
        ));
    }


    @GetMapping("/eventStore/{accountID}")
    public Stream eventStore(@PathVariable String accountID){
        return eventStore.readEvents(accountID).asStream();
    }
}
