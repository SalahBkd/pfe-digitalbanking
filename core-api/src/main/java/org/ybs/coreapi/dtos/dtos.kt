package org.ybs.coreapi.dtos

data class CustomerRequestDTO(
        var name:String="",
        var email : String="",
        var phoneNumber : String = "",
        var address : String = ""
)