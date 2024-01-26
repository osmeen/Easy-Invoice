package com.osmeen.easyinvoice.ui.theme.ui.auth.home

import android.os.Bundle
import androidx.activity.compose.setContent
import com.osmeen.easyinvoice.ui.theme.ui.BaseActivity
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.nav.HomeNavHost
import com.osmeen.easyinvoice.ui.theme.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeNavHost()
            }
        }
    }
}