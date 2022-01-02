package org.ybs.coreapi.queries

// CUSTOMER
class GetAllCustomersQuery {}
data class GetCustomerByIdQuery(val id : String)

// ACCOUNT
class GetAllAccountsQuery {}
data class GetAccountByIdQuery(val id : String)