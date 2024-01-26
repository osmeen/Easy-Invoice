package com.osmeen.easyinvoice.ui.theme.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.osmeen.easyinvoice.ui.theme.data.auth.AuthRepository
import com.osmeen.easyinvoice.ui.theme.data.auth.AuthRepositoryImpl
import com.osmeen.easyinvoice.ui.theme.data.home.CustomersRepository
import com.osmeen.easyinvoice.ui.theme.data.home.CustomersRepositoryImpl
import com.osmeen.easyinvoice.ui.theme.data.home.DashboardRepository
import com.osmeen.easyinvoice.ui.theme.data.home.DashboardRepositoryImpl
import com.osmeen.easyinvoice.ui.theme.data.home.InvoiceRepository
import com.osmeen.easyinvoice.ui.theme.data.home.InvoiceRepositoryImpl
import com.osmeen.easyinvoice.ui.theme.data.home.MyBusinessRepository
import com.osmeen.easyinvoice.ui.theme.data.home.MyBusinessRepositoryImpl
import com.osmeen.easyinvoice.ui.theme.data.home.TaxRepository
import com.osmeen.easyinvoice.ui.theme.data.home.TaxRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseDb(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideMyBusinessRepository(impl: MyBusinessRepositoryImpl): MyBusinessRepository = impl

    @Provides
    fun provideCustomersRepository(impl: CustomersRepositoryImpl): CustomersRepository = impl

    @Provides
    fun provideTaxRepository(impl: TaxRepositoryImpl): TaxRepository = impl

    @Provides
    fun provideInvoiceRepository(impl: InvoiceRepositoryImpl): InvoiceRepository = impl

    @Provides
    fun provideDashboardRepository(impl: DashboardRepositoryImpl): DashboardRepository = impl



}