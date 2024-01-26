package com.osmeen.easyinvoice.ui.theme.data.home

import com.osmeen.easyinvoice.ui.theme.data.Resource
import com.osmeen.easyinvoice.ui.theme.data.models.Tax

interface TaxRepository {
    suspend fun getTaxes(): Resource<List<Tax>>
    suspend fun addTax(tax: Tax): Resource<Tax>
    suspend fun updateTax(tax: Tax): Resource<Tax>
    suspend fun deleteTax(id: String): Resource<Boolean>
}