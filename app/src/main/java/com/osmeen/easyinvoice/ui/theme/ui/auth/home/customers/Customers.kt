package com.osmeen.easyinvoice.ui.theme.ui.auth.home.customers

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.osmeen.easyinvoice.R
import com.osmeen.easyinvoice.ui.theme.data.Resource
import com.osmeen.easyinvoice.ui.theme.ui.AppScreen
import com.osmeen.easyinvoice.ui.theme.ui.commons.EmptyScreen
import com.osmeen.easyinvoice.ui.theme.ui.commons.FullScreenProgressbar
import com.osmeen.easyinvoice.ui.theme.ui.faker.FakeViewModelProvider
import com.osmeen.easyinvoice.ui.theme.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Customers(viewModel: CustomersViewModel, navController: NavController) {
    val context = LocalContext.current
    val customers = viewModel.customers.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.setUpdating(null)
                    viewModel.validateInputs()
                    navController.navigate(AppScreen.Customers.ManageCustomer.route)
                }
            ) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.empty))
            }
        },
        content = {
            customers.value?.let {
                when (it) {
                    is Resource.Failure -> {
                        Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                    }
                    Resource.Loading -> {
                        FullScreenProgressbar()
                    }
                    is Resource.Success -> {
                        if (it.result.isEmpty()) {
                            EmptyScreen(title = stringResource(id = R.string.empty_customer)) {

                            }
                        } else {
                            LazyColumn {
                                items(it.result) { item ->
                                    Customer(
                                        customer = item,
                                        onClick = {
                                            viewModel.setUpdating(item)
                                            navController.navigate(AppScreen.Customers.ManageCustomer.route)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CustomersPreviewLight() {
    AppTheme {
        Customers(FakeViewModelProvider.provideCustomersViewModel(), rememberNavController())
    }
}