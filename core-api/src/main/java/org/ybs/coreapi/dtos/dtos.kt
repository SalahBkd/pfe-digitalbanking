package org.ybs.coreapi.dtos

import org.ybs.coreapi.enums.AccountStatus

// CUSTOMER
data class CreateCustomerRequestDTO(
        var name:String="",
        var email : String="",
        var phoneNumber : String = "",
        var address : String = ""
)

data class UpdateCustomerRequestDTO(
        var customerID : String = "",
        var name:String = "",
        var email : String="",
        var phoneNumber : String = "",
        var address : String = ""
)

data class DeleteCustomerRequestDTO(
        var customerID : String = "",
)

// ACCOUNT
data class CreateAccountRequestDTO(
        var initialBalance : Double = 0.0,
        var currency : String = "",
        var status : AccountStatus ?= null
)

data class CreditAccountRequestDTO(
        var accountID : String = "",
        var amount : Double = 0.0,
        var currency : String = "",
)

data class DebitAccountRequestDTO(
        var accountID : String = "",
        var amount : Double = 0.0,
        var currency : String = "",
)

data class DeleteAccountRequestDTO(
        var accountID : String = "",
)


