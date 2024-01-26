package com.osmeen.easyinvoice.ui.theme.ui.auth.home.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.osmeen.easyinvoice.ui.theme.ui.AppScreen
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.customers.Customers
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.customers.CustomersViewModel
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.customers.ManageCustomer
import com.osmeen.easyinvoice.ui.theme.ui.utils.getViewModelInstance

fun NavGraphBuilder.customersNav(navController: NavController) {
    navigation(
        startDestination = AppScreen.Customers.Home.route,
        route = AppScreen.Customers.route,
    ) {
        composable(AppScreen.Customers.Home.route) {
            val vm = navController.getViewModelInstance<CustomersViewModel>(it, AppScreen.Customers.route)
            Customers(vm, navController)
        }

        composable(AppScreen.Customers.ManageCustomer.route) {
            val vm = navController.getViewModelInstance<CustomersViewModel>(it, AppScreen.Customers.route)
            ManageCustomer(vm, navController)
        }
    }
}