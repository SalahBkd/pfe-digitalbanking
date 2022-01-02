package org.ybs.accountqueryside.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ybs.accountqueryside.entities.Account;

public interface AccountRepo extends JpaRepository<Account, String> {
}
