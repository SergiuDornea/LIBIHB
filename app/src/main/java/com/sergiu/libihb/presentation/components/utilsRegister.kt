package com.sergiu.libihb.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt1
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sergiu.libihb.R
import com.sergiu.libihb.presentation.events.LogInFormEvent
import com.sergiu.libihb.presentation.events.RegisterFormEvent
import com.sergiu.libihb.presentation.navigation.AppScreens
import com.sergiu.libihb.presentation.screens.register.RegisterFormState
import com.sergiu.libihb.presentation.screens.register.RegisterViewModel
import com.sergiu.libihb.presentation.screens.register.ValidationEventReg

//TODO REGISTRATION SCREEN UTILS


@Composable
// a registration  in form containing four custom input text fields,
// user name, email, password, phone number
// and a submit button

fun RegistrationForm(
    navController : NavController,
    isLoading : Boolean = false,// use it to enable/disable submit button if form empty or partially empty
){

    // a variable used to know how to toggle between the visibility of the password - we want to allow the users to see
    // what they have typed in the password input field, if wanted or needed
    val passwordIsVisible = rememberSaveable { mutableStateOf(false) } // initially wee do not want to see the password
    val viewModel = viewModel<RegisterViewModel>() // the corresponding view model
    val formState =viewModel.formState

    //TODO grasp on this
    // when the subbmit button is clicked
    LaunchedEffect(key1 = null){
        viewModel.validationEventsReg.collect() {
            if(it is ValidationEventReg.Success){
                Log.d("reg", "mere ceva")
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
        //custom name input field
        CustomNameInputTextFieldReg(
            viewModel = viewModel ,
            formState = formState,
            isEnabled = !isLoading)
        Spacer(modifier = Modifier.padding(top = 16.dp))

        // custom email input field
        CustomEmailInputTextFieldReg(
            viewModel = viewModel,
            formState = formState,
            isEnabled = !isLoading)
        Spacer(modifier = Modifier.padding(top = 16.dp))

        // custom phone input
        CustomPhoneInputTextFieldReg(
            viewModel =viewModel ,
            formState = formState,
            isEnabled = !isLoading)
        Spacer(modifier = Modifier.padding(top = 16.dp))

        // custom password field
        CustomPasswordInputTextFieldReg(
            viewModel = viewModel,
            formState = formState,
            isEnabled = !isLoading,
            passwordIsVisible = passwordIsVisible
        )


        Spacer(modifier = Modifier.padding(top = 65.dp))
        // custom  submit button
        SubmitButtonReg(
            btnText = stringResource(id = R.string.register_text),
            btnIcon = Icons.Default.PersonAddAlt1,
            btnIconDescription = stringResource(id = R.string.register_icon_description),
            viewModel = viewModel,
            isLoading = isLoading)

        // if no account - navigate to register screen
        goTo(
            navController =navController ,
            route = AppScreens.LogInScreen.name,
            text1 = stringResource(id = R.string.already_have_acc),
            linkText = stringResource(id = R.string.login_here)
        )

    }


}


// Name input text field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNameInputTextFieldReg(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    formState: RegisterFormState,
    isEnabled: Boolean,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = formState.name,
            onValueChange = { name ->
                viewModel.whenEventHappens(RegisterFormEvent.NameChanged(name))
            },
            enabled = isEnabled,
            singleLine = true,
            isError = formState.nameError != null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions.Default,
            label = {
                Text(text = (stringResource(id = R.string.name_label)))
            },

            )

        // Add space only if there is an error
        if (formState.nameError != null) {
            Row {
                Text(
                    text = formState.nameError,
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


// Email input text field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEmailInputTextFieldReg(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    formState: RegisterFormState,
    isEnabled: Boolean,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = formState.email,
            onValueChange = { email ->
                viewModel.whenEventHappens(RegisterFormEvent.EmailChanged(email))
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


// Password input text field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordInputTextFieldReg(
    modifier: Modifier = Modifier, // make it optional
    viewModel: RegisterViewModel,
    formState: RegisterFormState,
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
                viewModel.whenEventHappens(RegisterFormEvent.PasswordChanged(password))
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

// Phone input text field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPhoneInputTextFieldReg(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    formState: RegisterFormState,
    isEnabled: Boolean,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = formState.phone,
            onValueChange = { phone ->
                viewModel.whenEventHappens(RegisterFormEvent.PhoneChanged(phone))
            },
            enabled = isEnabled,
            singleLine = true,
            isError = formState.phoneError != null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions.Default,
            label = {
                Text(text = (stringResource(id = R.string.phone_label)))
            },

            )

        // Add space only if there is an error
        if (formState.phoneError != null) {
            Row {
                Text(
                    text = formState.phoneError,
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


// Submit btn
@Composable
fun SubmitButtonReg(
    btnText: String,
    btnIcon: ImageVector,
    btnIconDescription: String,
    viewModel: RegisterViewModel,
    isLoading: Boolean
){
    Button(
        onClick = {
            viewModel.whenEventHappens(RegisterFormEvent.SubmitClicked)
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

