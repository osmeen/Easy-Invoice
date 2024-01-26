package com.osmeen.easyinvoice.ui.theme.ui.auth

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.osmeen.easyinvoice.ui.theme.ui.BaseActivity
import com.osmeen.easyinvoice.ui.theme.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AuthNavHost(rememberNavController())
            }
        }
    }
}