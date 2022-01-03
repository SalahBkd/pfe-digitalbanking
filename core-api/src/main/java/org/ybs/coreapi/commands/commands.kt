package org.ybs.coreapi.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.ybs.coreapi.enums.AccountStatus
import java.util.*

abstract class BaseCommand<T>(
        @TargetAggregateIdentifier
        open val id : T
)

// CUSTOMER
data class CreateCustomerCommand(
        override val id : String,
        val name : String,
        val email : String,
        val phoneNumber : String,
        val address : String
):BaseCommand<String>(id)

data class UpdateCustomerCommand(
        override val id : String,
        val name : String,
        val email : String,
        val phoneNumber : String,
        val address : String
):BaseCommand<String>(id)

data class DeleteCustomerCommand(
        override val id : String,
):BaseCommand<String>(id)

// ACCOUNT
data class CreateAccountCommand(
        override val id : String,
        val balance : Double,
        val currency : String
):BaseCommand<String>(id)

data class CreditAccountCommand(
        override val id : String,
        val amount : Double,
        val currency : String,
        val date : Date,
):BaseCommand<String>(id)

data class DebitAccountCommand(
        override val id : String,
        val amount : Double,
        val currency : String,
        val date : Date,
):BaseCommand<String>(id)

data class DeleteAccountCommand(
        override val id : String,
):BaseCommand<String>(id)

