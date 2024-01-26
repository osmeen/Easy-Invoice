package com.osmeen.easyinvoice.ui.theme.ui.auth

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.osmeen.easyinvoice.R
import com.osmeen.easyinvoice.ui.theme.data.Resource
import com.osmeen.easyinvoice.ui.theme.ui.AppScreen
import com.osmeen.easyinvoice.ui.theme.ui.auth.home.HomeActivity
import com.osmeen.easyinvoice.ui.theme.ui.faker.FakeViewModelProvider
import com.osmeen.easyinvoice.ui.theme.ui.theme.AppTheme
import com.osmeen.easyinvoice.ui.theme.ui.theme.spacing
import com.osmeen.easyinvoice.ui.theme.ui.utils.startNewActivity
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SignupScreen(viewModel: AuthViewModel, navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val signupFlow = viewModel.signupFlow.collectAsState()

    val bringIntoViewRequester = BringIntoViewRequester()
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (refHeader, refName, refEmail, refPassword, refButtonSignup, refTextSignUp, refLoader) = createRefs()
        val spacing = MaterialTheme.spacing

        Box(
            modifier = Modifier
                .constrainAs(refHeader) {
                    top.linkTo(parent.top, spacing.extraLarge)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .wrapContentSize()
        ){
            AuthHeader()
        }
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(text = stringResource(id = R.string.name))
            },
            modifier = Modifier
                .constrainAs(refName) {
                    top.linkTo(refHeader.bottom, spacing.extraLarge)
                    start.linkTo(parent.start, spacing.large)
                    end.linkTo(parent.end, spacing.large)
                    width = Dimension.fillToConstraints
                }
                .onFocusEvent {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            modifier = Modifier
                .constrainAs(refEmail) {
                    top.linkTo(refName.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.large)
                    end.linkTo(parent.end, spacing.large)
                    width = Dimension.fillToConstraints
                }
                .onFocusEvent {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            modifier = Modifier
                .constrainAs(refPassword) {
                top.linkTo(refEmail.bottom, spacing.medium)
                start.linkTo(parent.start, spacing.large)
                end.linkTo(parent.end, spacing.large)
                width = Dimension.fillToConstraints
            }
                .onFocusEvent {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Button(
            onClick = {
                viewModel.signup(name.trim(), email.trim(), password.trim())
            },
            modifier = Modifier.constrainAs(refButtonSignup) {
                top.linkTo(refPassword.bottom, spacing.large)
                start.linkTo(parent.start, spacing.extraLarge)
                end.linkTo(parent.end, spacing.extraLarge)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(text = stringResource(id = R.string.signup), style = MaterialTheme.typography.titleMedium)
        }

        Text(
            modifier = Modifier
                .constrainAs(refTextSignUp) {
                    top.linkTo(refButtonSignup.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.extraLarge)
                    end.linkTo(parent.end, spacing.extraLarge)
                }
                .clickable {
                    navController.navigate(AppScreen.Auth.Login.route) {
                        popUpTo(AppScreen.Auth.Signup.route) { inclusive = true }
                    }
                }
                .bringIntoViewRequester(bringIntoViewRequester),
            text = stringResource(id = R.string.already_have_account),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary
        )
        signupFlow.value?.let {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.constrainAs(refLoader) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                }
                //      when the login is success we will navigate the user to the HomeActivity
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        context.startNewActivity(HomeActivity::class.java)

                    }
                }

            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SignupScreenPrevLight() {
    AppTheme {
        SignupScreen(FakeViewModelProvider.provideAuthViewModel(), rememberNavController())
    }
}