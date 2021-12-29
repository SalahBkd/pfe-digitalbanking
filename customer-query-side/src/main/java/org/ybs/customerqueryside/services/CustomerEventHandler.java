package org.ybs.customerqueryside.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import org.ybs.coreapi.events.CustomerCreatedEvent;
import org.ybs.customerqueryside.entities.Customer;
import org.ybs.customerqueryside.repositories.CustomerRepo;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerEventHandler {
    private CustomerRepo customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event){
        log.info("************************");
        log.info("CustomerCreatedEvent received");
        Customer customer=new Customer();
        customer.setId(event.getId());
        customer.setName(event.getName());
        customer.setEmail(event.getEmail());
        customer.setPhoneNumber(event.getPhoneNumber());
        customer.setAddress(event.getAddress());
        customerRepository.save(customer);
    }

}
