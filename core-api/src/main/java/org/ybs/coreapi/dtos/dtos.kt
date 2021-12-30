package org.ybs.coreapi.dtos

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