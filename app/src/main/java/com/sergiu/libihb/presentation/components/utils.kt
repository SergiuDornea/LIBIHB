package com.sergiu.libihb.presentation.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sergiu.libihb.R
import com.sergiu.libihb.presentation.events.LogInFormEvent
import com.sergiu.libihb.presentation.navigation.AppScreens
import com.sergiu.libihb.presentation.screens.login.LogInFormState
import com.sergiu.libihb.presentation.screens.login.LogInViewModel
import com.sergiu.libihb.presentation.screens.login.ValidationEvent

@Preview
@Composable
// app logo to display around
fun Logo(modifier: Modifier = Modifier){
    Image(
        painter = painterResource(id = R.drawable.ic_logo_transparent2),
        contentDescription = stringResource(id = R.string.app_logo),
        modifier = Modifier
            .padding(top = 30.dp)
            .size(180.dp)
            .border(
                border = BorderStroke(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
                shape = CircleShape
            ),
        alignment = Alignment.TopCenter
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
// a log in form containing two custom input text fields,
// one for user email, and one for user password
// and a submit button
// this form is tied up to FIREBASE so that the log in actually happens
fun LogForm(
    navController : NavController,
    isLoading : Boolean = false ,// use it to enable/disable submit button if form empty or partially empty
){

    // a variable used to know how to toggle between the visibility of the password - we want to allow the users to see
    // what they have typed in the password input field, if wanted or needed
    val passwordIsVisible = rememberSaveable { mutableStateOf(false) } // initially wee do not want to see the password

    val viewModel = viewModel<LogInViewModel>()
    val formState =viewModel.formState
    //TODO grasp on this
    // when the subbmit button is clicked
    LaunchedEffect(key1 = null){
        viewModel.validationEvents.collect() {
            if(it is ValidationEvent.Success){
                Log.d("srg", "1${formState.password} 2${formState.email}")
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxSize()
    ) {
        // custom email input field
        CustomEmailInputTextField(
            viewModel = viewModel,
            formState = formState,
            isEnabled = !isLoading)
        Spacer(modifier = Modifier.padding(top = 25.dp))

        // custom password field
        CustomPasswordInputTextField(
            viewModel = viewModel,
            formState = formState,
            isEnabled = !isLoading,
            passwordIsVisible = passwordIsVisible
        )

        Spacer(modifier = Modifier.padding(top = 35.dp))
        // custom  submit button
        SubmitButton(
            btnText = stringResource(id = R.string.login_text),
            btnIcon = Icons.Default.Login,
            btnIconDescription = stringResource(id = R.string.login_icon_description),
            viewModel = viewModel,
            isLoading = isLoading)

        // if no account - navigate to register screen
        goToRegister(navController = navController)

    }


}



// a custom submit button usable for both log in and sign in
// to reuse code properly
@Composable
fun SubmitButton(
    btnText: String,
    btnIcon: ImageVector,
    btnIconDescription: String,
    viewModel: LogInViewModel,
    isLoading: Boolean
    ){
    Button(
        onClick = {
                  viewModel.whenEventHappens(LogInFormEvent.SubmitClicked)
        },
        modifier = Modifier
            .width(250.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(bottomStart = 35.dp, topEnd = 35.dp))
    ){
        if (!isLoading){
            Icon(
                imageVector = btnIcon,
                contentDescription = btnIconDescription,
            )
            Spacer(modifier = Modifier.padding(end = 7.dp))
            Text(
                text = btnText,
                fontWeight = FontWeight.Bold,)
        }else{
            // if it is loading display a loading indicator to inform the user that something is happening
            CircularProgressIndicator()
        }
    }


}


// Email input text field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEmailInputTextField(
    modifier: Modifier = Modifier,
    viewModel: LogInViewModel,
    formState: LogInFormState,
    isEnabled: Boolean,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = formState.email,
            onValueChange = { email ->
                viewModel.whenEventHappens(LogInFormEvent.EmailChanged(email))
            },
            enabled = isEnabled,
            singleLine = true,
            isError = formState.emailError != null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions.Default,
            label = {
                Text(text = (stringResource(id = R.string.email_label)))
            },

        )

        // Add space only if there is an error
        if (formState.emailError != null) {
            Row {
                Text(
                    text = formState.emailError,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Start

                )
            }
        }
    }
}


//Password input text field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordInputTextField(
    modifier: Modifier = Modifier, // make it optional
    viewModel: LogInViewModel,
    formState: LogInFormState,
    isEnabled: Boolean,
    passwordIsVisible: MutableState<Boolean>, // used to shift the visual transformation of the text field

) {
    // a variable used to set the visual transformation
    // - change the visual output for password text field
    // if the visibility of the password is true - do not set any visual Transformation
    val showPassword = if (passwordIsVisible.value)
        VisualTransformation.None else PasswordVisualTransformation() // function to visual transform the text
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {

        OutlinedTextField(
            value = formState.password,
            onValueChange = { password ->
                viewModel.whenEventHappens(LogInFormEvent.PasswordChanged(password))
            },
            enabled = isEnabled,// set the enabled value to the value of the parameter passed
            singleLine = true, // allow only a single line for the field
            isError = formState.passwordError != null, // use != null to resolve null safety
            // set the keyboard type to EMAIL
            //- once the email was typed go to the next text field using the keyboard - input method editor - to enhance user experience
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            // the action that will be triggered after the user ime action will be set to Default
            keyboardActions = KeyboardActions.Default,
            label = {
                Text(text = (stringResource(id = R.string.password_label)))
            },
            // transform the visible text
            visualTransformation = showPassword,
            //toggle between the state of passwordIsVisible
            trailingIcon = {
                IconToggleButton(
                    checked = passwordIsVisible.value,
                    onCheckedChange = {
                        // change the value onclick to the opposite one
                        passwordIsVisible.value = !passwordIsVisible.value
                    }) {
                    if (passwordIsVisible.value) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = stringResource(id = R.string.visible_password)
                        )

                    } else {
                        Icon(
                            imageVector = Icons.Default.VisibilityOff,
                            contentDescription = stringResource(id = R.string.invisible_password)
                        )
                    }
                }
            }
        )
        // Add space only if there is an error
        if (formState.passwordError != null) {
            if (formState.passwordError == "The password must include: lowercase and uppercase letters, and digits") {
                Text(
                    text = "The password must contain:\nletters (both lowercase and uppercase) and digits",
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Start
                )
            } else {
                Text(
                    text = formState.passwordError,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}


@Composable
fun goToRegister(navController: NavController){
    Row(
        modifier = Modifier
            .padding(top = 120.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.new_user)
        )
        Text(
            text = stringResource(id = R.string.register_here),
            modifier = Modifier
                .clickable {
                navController.navigate(AppScreens.RegisterScreen.name)
            }
            .padding(start = 5.dp),
            fontWeight = FontWeight.Bold,
        )
    }
}


