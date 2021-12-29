package org.ybs.coreapi.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

abstract class BaseCommand<T>(
        @TargetAggregateIdentifier
        open val id : T
)
data class CreateCustomerCommand(
        override val id : String,
        val name : String,
        val email : String,
        val phoneNumber : String,
        val address : String
):BaseCommand<String>(id)