package org.ybs.customerqueryside.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.ybs.coreapi.queries.GetAllCustomersQuery;
import org.ybs.coreapi.queries.GetCustomerByIdQuery;
import org.ybs.customerqueryside.entities.Customer;
import org.ybs.customerqueryside.repositories.CustomerRepo;

import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class CustomerQueryHandler {
    public CustomerRepo customerRepo;

    @QueryHandler
    public List<Customer> customerList(GetAllCustomersQuery query){
        return customerRepo.findAll();
    }

    @QueryHandler
    public Customer customer(GetCustomerByIdQuery query){
        return customerRepo.findById(query.getId())
                .orElseThrow(()->new RuntimeException("Customer not found !"));
    }
}
