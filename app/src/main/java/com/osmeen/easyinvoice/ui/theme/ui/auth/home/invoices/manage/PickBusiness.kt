package com.osmeen.easyinvoice.ui.theme.ui.auth.home.invoices.manage

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.osmeen.easyinvoice.ui.theme.data.models.Business
import com.osmeen.easyinvoice.ui.theme.ui.AppScreen
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.invoices.InvoicesViewModel
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.mybusinesses.MyBusiness
import com.osmeen.easyinvoice.ui.theme.ui.commons.EmptyScreen
import com.osmeen.easyinvoice.ui.theme.ui.commons.FullScreenProgressbar
import com.osmeen.easyinvoice.ui.theme.ui.faker.FakeViewModelProvider
import com.osmeen.easyinvoice.ui.theme.ui.theme.AppTheme
import com.osmeen.easyinvoice.ui.theme.ui.theme.spacing
import com.osmeen.easyinvoice.ui.theme.ui.utils.toast

@Composable
fun PickBusinessScreen(viewModel: InvoicesViewModel, navController: NavController) {
    val context = LocalContext.current
    val businesses = viewModel.businesses.collectAsState()

    businesses.value?.let {
        when (it) {
            is Resource.Failure -> {
                context.toast(it.exception.message!!)
            }
            Resource.Loading -> {
                FullScreenProgressbar()
            }
            is Resource.Success -> {
                PickBusiness(viewModel, it.result, navController)
            }
        }
    }
}

@Composable
fun PickBusiness(viewModel: InvoicesViewModel, businesses: List<Business>, navController: NavController) {
    val spacing = MaterialTheme.spacing
    if (businesses.isEmpty()) {
        EmptyScreen(title = stringResource(id = R.string.empty_business)) { }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.pick_a_business),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(spacing.medium)
            )

            LazyColumn {
                items(businesses) { item ->
                    MyBusiness(
                        business = item,
                        onClick = {
                            viewModel.setBusiness(item)
                            navController.navigate(AppScreen.Invoices.ManageInvoice.PickCustomer.route)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PickBusinessPreviewLight() {
    AppTheme {
        PickBusiness(
            FakeViewModelProvider.provideInvoicesViewModel(),
            listOf(
                Business(
                    name = "Othman jamal",
                    address = "Dar es salaam, Tanzania",
                    email = "othmanjmal07@gmail.com",
                    phone = "0789991831"
                ),
                Business(
                    name = "Othman jamal",
                    address = "Dar es salaam, Tanzania",
                    email = "othmanjmal07@gmail.com",
                    phone = "0789991831"
                )
            ),
            rememberNavController()
        )
    }
}
