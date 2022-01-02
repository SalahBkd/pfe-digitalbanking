package org.ybs.accountqueryside.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ybs.coreapi.enums.AccountStatus;

import javax.persistence.*;
import java.util.Collection;

@Entity @Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private String id;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private String currency;
    // TODO Eager must be fixed later, know how to reset events data in axon
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Collection<Operation> operations;
}
