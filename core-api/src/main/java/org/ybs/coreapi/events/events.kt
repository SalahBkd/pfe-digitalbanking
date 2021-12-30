package org.ybs.coreapi.events

abstract class BaseEvent<T>(
        open val id : T
)
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