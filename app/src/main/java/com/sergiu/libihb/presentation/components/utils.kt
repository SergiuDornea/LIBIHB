package com.sergiu.libihb.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sergiu.libihb.R

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
    isLoading : Boolean = false ,// use it to enable/disable submit button if form empty or partially empty
) {
    // variables necessary
    // i choose to use "rememberSaveable" instead of just "remember" because my option remembers the password
    // value for this example beyond any user configuration changes as: rotating the screen or interrupting the typing
    val password = rememberSaveable { mutableStateOf("") } // a mutable state of string holds the value of the inputted password
    val email = rememberSaveable { mutableStateOf("") } // a mutable state of string holds the value of the inputted email

    // a variable used to know how to toggle between the visibility of the password - we want to allow the users to see
    // what they have typed in the password input field, if wanted or needed
    val passwordIsVisible = rememberSaveable { mutableStateOf(false) } // initially wee do not want to see the password

    // save a reference to the local software keyboard controller to get control over the keyboard whenever needed
    val controllerForKeyboard = LocalSoftwareKeyboardController.current

    // variable holding a boolean value if the form is eligible for submission
    // insert into the remember constructor the values we want to check - password and email
    val isFormValid = remember(password.value, email.value ) {
        // trim the data to be sure that every value has some type of a character, and check if it not empty
        password.value.trim().isNotEmpty() && email.value.trim().isNotEmpty()
    }

    val isPasswordValid = remember(password.value) {
        password.value.trim().isNotEmpty()
    }
    val isEmailValid = remember(password.value) {
        email.value.trim().isNotEmpty()
    }

    // use a Column to lay everything vertically
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        // custom email input
        CustomEmailInputTextField(
            emailText = email,
            isEnabled = !isLoading
            )
        Spacer(modifier = Modifier.padding(top = 25.dp))
        // custom password input
        CustomPasswordInputTextField(
            passwordText = password,
            isEnabled = !isLoading,
            passwordIsVisible = passwordIsVisible,
            isFormValid = isFormValid,

        )

        // custom  submit button


    }
}


// Email input text field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEmailInputTextField(
    modifier: Modifier = Modifier, // make it optional
    emailText : MutableState<String>, // holds the value typed into the text field
    isEnabled: Boolean
) {
    OutlinedTextField(
        value = emailText.value,
        onValueChange ={ // if the value changes reassign the new value to the emailText
            emailText.value = it
        },

        enabled = isEnabled, // set the enabled value to the value of the parameter passed
        singleLine = true, // allow only a single line for the field

        // set the keyboard type to EMAIL
        //- once the email was typed go to the next text field using the keyboard - input method editor - to enhance user experience
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
        // the action that will be triggered after the user ime action will be set to Default
        keyboardActions = KeyboardActions.Default,
        label = {
            Text(text = (stringResource(id = R.string.email_label)))
        }
    )

}


//Password input text field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordInputTextField(
    modifier: Modifier = Modifier, // make it optional
    passwordText: MutableState<String>, // holds the value typed into the text field
    isEnabled: Boolean,
    passwordIsVisible: MutableState<Boolean>, // used to shift the visual transformation of the text field
    isFormValid: Boolean // if form not valid use this to disable submit button

    ) {
    // a variable used to set the visual transformation
    // - change the visual output for password text field
    // if the visibility of the password is true - do not set any visual Transformation
    val showPassword = if(passwordIsVisible.value)
        VisualTransformation.None else PasswordVisualTransformation() // function to visual transform the text

    OutlinedTextField(
        value = passwordText.value,
        onValueChange ={ // if the value changes reassign the new value to the passwordText
            passwordText.value = it
        },
        // transform the visible text
        visualTransformation = showPassword,
        //toggle between the state of passwordIsVisible
        trailingIcon = {
                       IconToggleButton(
                           checked = passwordIsVisible.value,
                           onCheckedChange ={
                                // change the value onclick to the opposite one
                               passwordIsVisible.value = !passwordIsVisible.value
                           } ) {
                               Icon(
                                   imageVector = Icons.Default.RemoveRedEye,
                                   contentDescription =  stringResource(id = R.string.visible_password)
                               )
                       }
        },

//        // supporting text to inform the user that the password should be at least 6 characters long
//        supportingText = {
//                         Text(text = stringResource(id = R.string.password_requirement))
//        },
        enabled = isEnabled, // set the enabled value to the value of the parameter passed
        singleLine = true, // allow only a single line for the field

        // set the keyboard type to PASSWORD
        // input method editor - to enhance user experience - show "done" btn on keyboard
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),

//        TODO remove this if useless
// the action that will be triggered after the user ime action will be set to submit the data
//        keyboardActions = KeyboardActions{
//            if (!isFormValid) return@KeyboardActions// if condition not true exit the current block of code
//            onFormCompleted()
//        },
        label = {
            Text(text = (stringResource(id = R.string.password_label)))
        }
    )

}