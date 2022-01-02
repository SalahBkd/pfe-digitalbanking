package org.ybs.coreapi.events

import org.ybs.coreapi.enums.AccountStatus
import java.util.*

abstract class BaseEvent<T>(
        open val id : T
)

// CUSTOMER
data class CustomerCreatedEvent(
        override val id:String,
        val name : String,
        val email : String,
        val phoneNumber : String,
        val address : String
):BaseEvent<String>(id)

data class CustomerUpdatedEvent(
        override val id:String,
        val name : String,
        val email : String,
        val phoneNumber : String,
        val address : String
):BaseEvent<String>(id)

data class CustomerDeletedEvent(
        override val id:String,
):BaseEvent<String>(id)

// ACCOUNT
data class AccountCreatedEvent(
        override val id : String,
        val balance : Double,
        val currency : String,
        val status: AccountStatus
):BaseEvent<String>(id)

data class AccountActivatedEvent(
        override val id:String,
        val status: AccountStatus
):BaseEvent<String>(id)

data class AccountCreditedEvent(
        override val id : String,
        val amount : Double,
        val currency : String,
        val date: Date
):BaseEvent<String>(id)

data class AccountDebitedEvent(
        override val id : String,
        val amount : Double,
        val currency : String,
        val date: Date
):BaseEvent<String>(id)



