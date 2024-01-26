package com.osmeen.easyinvoice.ui.theme.data.home

import com.osmeen.easyinvoice.ui.theme.data.Resource
import com.osmeen.easyinvoice.ui.theme.data.models.Customer

interface CustomersRepository {
    suspend fun getCustomers(): Resource<List<Customer>>
    suspend fun addCustomer(customer: Customer): Resource<Customer>
    suspend fun updateCustomer(customer: Customer): Resource<Customer>
    suspend fun deleteCustomer(id: String): Resource<Boolean>
}