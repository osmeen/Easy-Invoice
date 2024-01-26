package com.osmeen.easyinvoice.ui.theme.ui.auth.drawer

import android.content.res.Configuration
import android.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.osmeen.easyinvoice.R
import com.osmeen.easyinvoice.ui.theme.ui.AppScreen
import com.osmeen.easyinvoice.ui.theme.ui.theme.AppTheme
import com.osmeen.easyinvoice.ui.theme.ui.theme.spacing

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    navController: NavController,
    onDestinationClicked: (route: String) -> Unit
) {
    val screens = listOf(
        AppScreen.Dashboard,
        AppScreen.Invoices,
        AppScreen.Taxes,
        AppScreen.MyBusinesses,
        AppScreen.Customers,
        AppScreen.Logout
    )

    val spacing = MaterialTheme.spacing
    Column(
        modifier = modifier
            .width(spacing.drawerWidth)
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(start = spacing.drawerItemPadding, end = spacing.drawerItemPadding, top = spacing.xLarge)
    ) {

        Image(
            modifier = Modifier
                .padding(spacing.drawerIconPadding)
                .size(spacing.xLarge),
            painter = painterResource(R.drawable.ic_app_logo),
            contentDescription = stringResource(id = R.string.app_name)
        )

        screens.forEach { screen ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(spacing.drawerItemHeight)
                    .padding(top = spacing.medium)
                    .clip(RoundedCornerShape(spacing.drawerCornerRadius))
                    .also {
                        val background = if (navController.currentDestination?.route == screen.route) {
                            MaterialTheme.colorScheme.tertiaryContainer
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                        it.background(background)
                    }
                    .clickable {
                        onDestinationClicked.invoke(screen.route)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(start = spacing.drawerIconPadding, end = spacing.drawerIconPadding)
                        .size(spacing.drawerIconSize),
                    painter = painterResource(id = screen.icon),
                    contentDescription = stringResource(id = screen.title)
                )

                Text(
                    text = stringResource(id = screen.title),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun DrawerPreviewLight() {
    AppTheme {
        Drawer(navController = rememberNavController(), onDestinationClicked = {})
    }
}