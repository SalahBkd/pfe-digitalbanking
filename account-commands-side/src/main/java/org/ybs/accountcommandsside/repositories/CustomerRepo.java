package org.ybs.accountcommandsside.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ybs.accountcommandsside.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer, String> {
}
