package com.osmeen.easyinvoice.ui.theme.data.home

import com.osmeen.easyinvoice.ui.theme.data.Resource
import com.osmeen.easyinvoice.ui.theme.data.models.Dashboard

interface DashboardRepository {
    suspend fun getDashboardInfo(): Resource<Dashboard>
}