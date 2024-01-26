package com.osmeen.easyinvoice.ui.theme.ui.auth.home

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.osmeen.easyinvoice.R
import com.osmeen.easyinvoice.ui.theme.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    @StringRes title: Int,
    buttonIcon: ImageVector = Icons.Filled.Menu,
    onButtonClicked: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

            TopAppBar(
            title = {
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = { onButtonClicked() }) {
                    Icon(
                        imageVector = buttonIcon,
                        contentDescription = stringResource(id = R.string.empty),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
        )
        content.invoke()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TopBarPreviewLight() {
    AppTheme {
        TopBar(title = R.string.dashboard, onButtonClicked = { }) { }
    }
}
