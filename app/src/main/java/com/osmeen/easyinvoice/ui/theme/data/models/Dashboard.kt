package com.osmeen.easyinvoice.ui.theme.data.models

data class Dashboard(
    val invoiceCount: Int,
    val receivedAmount: Double,
    val totalAmount: Double,
    val pendingInvoices: Int,
    val pendingAmount: Double
): BaseModel()

// Inherit the baseModel() class to create a Dashboard class model