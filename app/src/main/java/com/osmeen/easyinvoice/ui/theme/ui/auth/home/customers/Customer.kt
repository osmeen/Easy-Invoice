package com.osmeen.easyinvoice.ui.theme.ui.auth.home.customers

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.osmeen.easyinvoice.R
import com.osmeen.easyinvoice.ui.theme.data.models.Customer
import com.osmeen.easyinvoice.ui.theme.ui.theme.AppTheme
import com.osmeen.easyinvoice.ui.theme.ui.theme.spacing

@Composable
fun Customer(customer: Customer, onClick: () -> Unit) {
    val spacing = MaterialTheme.spacing
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = spacing.medium, end = spacing.medium, top = spacing.medium)
            .clickable { onClick.invoke() }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(spacing.medium)
        ) {
            val (refIcon, refName, refAddress) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_placeholder),
                contentDescription = stringResource(id = R.string.empty),
                modifier = Modifier
                    .size(spacing.xLarge)
                    .constrainAs(refIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )

            Text(
                text = customer.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(refName) {
                    start.linkTo(refIcon.end, spacing.small)
                    top.linkTo(refIcon.top)
                    end.linkTo(parent.end, spacing.medium)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = customer.getCompleteAddress(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.constrainAs(refAddress) {
                    start.linkTo(refName.start)
                    top.linkTo(refName.bottom, spacing.extraSmall)
                    end.linkTo(parent.end, spacing.medium)
                    width = Dimension.fillToConstraints
                }
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CustomerPreviewLight() {
    AppTheme {
        Customer(
            Customer(
                name = "Othman Jamal",
                address = "Dar es salaam, Tanzania",
                email = "othmanjmal07@gmail.com",
                phone = "0789991831"
            )
        ) { }
    }
}