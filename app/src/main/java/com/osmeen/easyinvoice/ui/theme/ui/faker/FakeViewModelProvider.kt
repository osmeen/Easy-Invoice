package com.osmeen.easyinvoice.ui.theme.ui.faker

//Currently there is a problem with *Jetpack Compose Preview* & *Hilt*
//Jetpack compose is not able to inject using hiltViewModel() to generate Compose Previews
//In future when both these libraries will be compatible, I can remove this object
//But for now, to see preview, I use this FakeViewModelProvider

import com.google.firebase.auth.FirebaseUser
import com.osmeen.easyinvoice.ui.theme.data.Resource
import com.osmeen.easyinvoice.ui.theme.data.auth.AuthRepository
import com.osmeen.easyinvoice.ui.theme.data.home.CustomersRepository
import com.osmeen.easyinvoice.ui.theme.data.home.DashboardRepository
import com.osmeen.easyinvoice.ui.theme.data.home.InvoiceRepository
import com.osmeen.easyinvoice.ui.theme.data.home.MyBusinessRepository
import com.osmeen.easyinvoice.ui.theme.data.home.TaxRepository
import com.osmeen.easyinvoice.ui.theme.data.models.Business
import com.osmeen.easyinvoice.ui.theme.data.models.Customer
import com.osmeen.easyinvoice.ui.theme.data.models.Dashboard
import com.osmeen.easyinvoice.ui.theme.data.models.Invoice
import com.osmeen.easyinvoice.ui.theme.data.models.Tax
import com.osmeen.easyinvoice.ui.theme.ui.auth.AuthViewModel
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.customers.CustomersViewModel
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.dashboard.DashboardViewModel
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.invoices.InvoicesViewModel
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.mybusinesses.MyBusinessesViewModel
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.taxes.TaxesViewModel

object FakeViewModelProvider {

    fun provideDashboardViewModel() = DashboardViewModel(dashboardRepo)

    fun provideInvoicesViewModel() = InvoicesViewModel(invoiceRepo, businessRepo, customersRepo, taxRepo)

    fun provideTaxesViewModel() = TaxesViewModel(taxRepo)

    fun provideCustomersViewModel() = CustomersViewModel(customersRepo)

    fun provideMyBusinessesViewModel() = MyBusinessesViewModel(businessRepo)

    fun provideAuthViewModel() = AuthViewModel(authRepo)

    private val dashboardRepo = object : DashboardRepository {
        override suspend fun getDashboardInfo(): Resource<Dashboard> {
            TODO("Not yet implemented")
        }
    }

    private val invoiceRepo = object : InvoiceRepository {
        override suspend fun getInvoices(): Resource<List<Invoice>> {
            TODO("Not yet implemented")
        }

        override suspend fun addInvoice(invoice: Invoice): Resource<Invoice> {
            TODO("Not yet implemented")
        }

        override suspend fun updateInvoice(invoice: Invoice): Resource<Invoice> {
            TODO("Not yet implemented")
        }

        override suspend fun deleteInvoice(id: String): Resource<Boolean> {
            TODO("Not yet implemented")
        }

        override suspend fun updatePaidState(id: String, isPaid: Boolean): Resource<Boolean> {
            TODO("Not yet implemented")
        }

    }

    private val taxRepo = object : TaxRepository {
        override suspend fun getTaxes(): Resource<List<Tax>> {
            TODO("Not yet implemented")
        }

        override suspend fun addTax(tax: Tax): Resource<Tax> {
            TODO("Not yet implemented")
        }

        override suspend fun updateTax(tax: Tax): Resource<Tax> {
            TODO("Not yet implemented")
        }
        override suspend fun deleteTax(id: String): Resource<Boolean> {
            TODO("Not yet implemented")
        }

    }

    private val customersRepo = object : CustomersRepository {
        override suspend fun getCustomers(): Resource<List<Customer>> {
            TODO("Not yet implemented")
        }

        override suspend fun addCustomer(customer: Customer): Resource<Customer> {
            TODO("Not yet implemented")
        }

        override suspend fun updateCustomer(customer: Customer): Resource<Customer> {
            TODO("Not yet implemented")
        }

        override suspend fun deleteCustomer(id: String): Resource<Boolean> {
            TODO("Not yet implemented")
        }

    }

    private val businessRepo = object : MyBusinessRepository {
        override suspend fun getMyBusinesses(): Resource<List<Business>> {
            TODO("Not yet implemented")
        }

        override suspend fun canAddBusiness(): Boolean {
            TODO("Not yet implemented")
        }

        override suspend fun addMyBusiness(business: Business): Resource<Business> {
            TODO("Not yet implemented")
        }

        override suspend fun updateMyBusiness(business: Business): Resource<Business> {
            TODO("Not yet implemented")
        }

        override suspend fun deleteMyBusiness(id: String): Resource<Boolean> {
            TODO("Not yet implemented")
        }
    }

    private val authRepo = object :     AuthRepository {
        override val currentUser: FirebaseUser?
            get() = null

        override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
            TODO("Not yet implemented")
        }

        override suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser> {
            TODO("Not yet implemented")
        }

        override fun logout() {
            TODO("Not yet implemented")
        }

    }
}