package com.osmeen.easyinvoice.ui.theme.ui.auth.home.mybusinesses

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.osmeen.easyinvoice.R
import com.osmeen.easyinvoice.ui.theme.data.Resource
import com.osmeen.easyinvoice.ui.theme.ui.AppScreen
import com.osmeen.easyinvoice.ui.theme.ui.commons.EmptyScreen
import com.osmeen.easyinvoice.ui.theme.ui.commons.FullScreenProgressbar
import com.osmeen.easyinvoice.ui.theme.ui.theme.AppTheme
import com.osmeen.easyinvoice.ui.theme.ui.utils.toast

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBusinesses(viewModel: MyBusinessesViewModel, navController: NavController) {
    val context = LocalContext.current

    val canAddBusiness = viewModel.canAddBusiness.collectAsState()
    val myBusinesses = viewModel.myBusinesses.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (canAddBusiness.value) {
                        viewModel.setUpdating(null)
                        viewModel.validateInputs()
                        navController.navigate(AppScreen.MyBusinesses.ManageMyBusiness.route)
                    } else {
                        context.toast(R.string.maximum_business_reached)
                    }
                }
            ) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.empty))
            }
        },
        content = {
            myBusinesses.value?.let {
                when (it) {
                    is Resource.Failure -> {
                        context.toast(it.exception.message!!)
                    }
                    Resource.Loading -> {
                        FullScreenProgressbar()
                    }
                    is Resource.Success -> {
                        if (it.result.isEmpty()) {
                            EmptyScreen(title = stringResource(id = R.string.empty_business)) { }
                        } else {
                            LazyColumn {
                                items(it.result) { item ->
                                    MyBusiness(
                                        business = item,
                                        onClick = {
                                            viewModel.setUpdating(item)
                                            navController.navigate(AppScreen.MyBusinesses.ManageMyBusiness.route)
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
fun MyBusinessesPreviewLight() {
    AppTheme {
        MyBusinesses(hiltViewModel(), rememberNavController())
    }
}
