package com.osmeen.easyinvoice.ui.theme.data.home

import com.google.firebase.auth.FirebaseAuth
import com.osmeen.easyinvoice.ui.theme.data.Resource
import com.osmeen.easyinvoice.ui.theme.data.models.Dashboard
import com.osmeen.easyinvoice.ui.theme.data.models.Invoice
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val invoiceRepository: InvoiceRepository,
    private val auth: FirebaseAuth
) : DashboardRepository {

    override suspend fun getDashboardInfo(): Resource<Dashboard> {
        val invoices = invoiceRepository.getInvoices()
        return try {
            Resource.Success(getDashboardInfo((invoices as Resource.Success).result))
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    private fun getDashboardInfo(invoices: List<Invoice>) = Dashboard(
        invoiceCount = invoices.size,
        receivedAmount = invoices.filter { it.isPaid() }.sumOf { it.invoiceAmount },
        totalAmount = invoices.sumOf { it.invoiceAmount },
        pendingInvoices = invoices.count { !it.isPaid() },
        pendingAmount = invoices.filter { !it.isPaid() }.sumOf { it.invoiceAmount },
    )
}