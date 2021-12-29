package org.ybs.customerqueryside.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ybs.customerqueryside.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer, String> {
}
