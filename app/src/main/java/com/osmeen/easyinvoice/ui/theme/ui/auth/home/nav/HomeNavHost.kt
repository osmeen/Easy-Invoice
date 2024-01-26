package com.osmeen.easyinvoice.ui.theme.ui.auth.home.nav

import android.content.res.Configuration
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.osmeen.easyinvoice.MainActivity
import com.osmeen.easyinvoice.ui.theme.ui.AppScreen
import com.osmeen.easyinvoice.ui.theme.ui.auth.AuthViewModel
import com.osmeen.easyinvoice.ui.theme.ui.auth.drawer.Drawer
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.TopBar
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.dashboard.DashboardScreen
import com.osmeen.easyinvoice.ui.theme.ui.theme.AppTheme
import com.osmeen.easyinvoice.ui.theme.ui.utils.startNewActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavHost() {
    val context = LocalContext.current
    val title = remember { mutableIntStateOf(AppScreen.Dashboard.title) }
    val navController = rememberNavController()

    val viewModel: AuthViewModel = hiltViewModel()

    Surface(color = MaterialTheme.colorScheme.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    navController = navController,
                    onDestinationClicked = { route ->
                        scope.launch { drawerState.close() }
                        if (route == AppScreen.Logout.route) {
                            viewModel.logout()
                            context.startNewActivity(MainActivity::class.java)
                        } else {
                            navController.navigate(route) {
                                launchSingleTop = true
                            }
                        }
                    }
                )
            }
        ) {
            TopBar(
                title = title.value,
                onButtonClicked = {
                    scope.launch {
                        drawerState.open()
                    }
                }, content = {
                    NavHost(
                        navController = navController,
                        startDestination = AppScreen.Dashboard.route
                    ) {
                        composable(AppScreen.Dashboard.route) { DashboardScreen(hiltViewModel()) }
                        invoiceNav(navController)
                        taxNav(navController)
                        businessNav(navController)
                        customersNav(navController)
                    }
                }
            )
        }
    }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title.value = backStackEntry.getTitle()
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeNavHostPrev() {
    AppTheme {
        HomeNavHost()
    }
}
