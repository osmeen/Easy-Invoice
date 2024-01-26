package com.osmeen.easyinvoice.ui.theme.ui.commons

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.osmeen.easyinvoice.R

@Composable
fun UserConfirmationDialog(
    onComplete: (shallDelete: Boolean) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onComplete.invoke(false) },
        confirmButton = {
            TextButton(
                onClick = { onComplete.invoke(true) },
                content = {
                    Text(
                        text = stringResource(id = R.string.ok),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            )

        },
        dismissButton = {
            TextButton(
                onClick = { onComplete.invoke(false) },
                content = {
                    Text(
                        text = stringResource(id = R.string.cancel)
                    )
                }
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.delete_confirmation),
                style = MaterialTheme.typography.titleMedium
            )
        }
    )
}