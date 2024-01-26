package com.osmeen.easyinvoice.ui.theme.ui.auth

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.osmeen.easyinvoice.ui.theme.ui.AppScreen

@Composable
fun AuthNavHost (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Auth.Login.route
    ){
        composable(AppScreen.Auth.Login.route){
            LoginScreen(hiltViewModel(), navController)
        }
        composable(AppScreen.Auth.Signup.route){
            SignupScreen(hiltViewModel(), navController)
        }
    }
}