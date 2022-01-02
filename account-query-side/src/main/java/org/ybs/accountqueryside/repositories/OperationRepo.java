package org.ybs.accountqueryside.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ybs.accountqueryside.entities.Operation;

public interface OperationRepo extends JpaRepository<Operation, Long> {
}
