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
    // TODO probelm
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Collection<Operation> operations;
}
