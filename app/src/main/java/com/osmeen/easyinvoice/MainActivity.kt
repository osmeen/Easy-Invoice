package com.osmeen.easyinvoice

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.osmeen.easyinvoice.ui.theme.ui.BaseActivity
import com.osmeen.easyinvoice.ui.theme.ui.auth.AuthNavHost
import com.osmeen.easyinvoice.ui.theme.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AuthNavHost(rememberNavController())
            }
        }
    }
}

