package com.osmeen.easyinvoice.ui.theme.data.home

import com.osmeen.easyinvoice.ui.theme.data.Resource
import com.osmeen.easyinvoice.ui.theme.data.models.Business

interface MyBusinessRepository {
    suspend fun getMyBusinesses(): Resource<List<Business>>
    suspend fun canAddBusiness(): Boolean
    suspend fun addMyBusiness(business: Business): Resource<Business>
    suspend fun updateMyBusiness(business: Business): Resource<Business>
    suspend fun deleteMyBusiness(id: String): Resource<Boolean>
}