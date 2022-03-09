package org.ybs.accountcommandsside.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import org.ybs.accountcommandsside.entities.Customer;
import org.ybs.accountcommandsside.repositories.CustomerRepo;
import org.ybs.coreapi.events.CustomerCreatedEvent;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerEventHandler {
    private CustomerRepo customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event){
        log.info("========================");
        log.info("CustomerCreatedEvent received from account-commands-side");
        Customer customer=new Customer();
        customer.setId(event.getId());
        customerRepository.save(customer);
    }

}
