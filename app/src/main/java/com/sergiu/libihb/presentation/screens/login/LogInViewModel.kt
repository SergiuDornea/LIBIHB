package com.sergiu.libihb.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergiu.libihb.domain.use_case_validate.ValidateEmail
import com.sergiu.libihb.domain.use_case_validate.ValidatePassword
import com.sergiu.libihb.presentation.events.LogInFormEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LogInViewModel(
    private val validPassword: ValidatePassword = ValidatePassword(),
    private val validEmail: ValidateEmail = ValidateEmail(),
) :ViewModel(){
    //create a wrapper class for the actual screen ui state
    // every value that can be changed at a given time will be stored here
    // uses the state of the use cases and pap the result to compose state
    // if password is valid display the new state on the screen

    var formState by mutableStateOf(LogInFormState())

    //TODO LOOK THIS UP
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    // a functions that receives the events from the log in screen
    fun whenEventHappens(event: LogInFormEvent){


        // when the password is changed
        if (event is LogInFormEvent.PasswordChanged){
            // we use copy because state email is immutable ,
            // so we change the state by creating a new state object with copy()
            // specific to data classes, and change the password of that copy
            formState = formState.copy(password = event.password)
        }
        // when the email is changed
        if(event is LogInFormEvent.EmailChanged){
            // like password
            formState = formState.copy(email = event.email)
        }
        // when event is submit clicked
        if(event is LogInFormEvent.SubmitClicked){
            // the most complex event
            onSubmit()

        }
    }


    // a function that checks if the inputted values are valid
    private fun onSubmit(){

        // values that hold validate state of the data
        val passValid = validPassword.validate(formState.password)
        val emailValid = validEmail.validate(formState.email)

        // if the submit button is not valid - we have an error
        if(!passValid.isValid || !emailValid.isValid){
            // map our form state again
            formState = formState.copy(
                passwordError = passValid.messageIfNotValid ,
                emailError =  emailValid.messageIfNotValid
            )
            return
        }
        //TODO a better documentation on corutines 26.00
        // if the validation is successfull this is executed
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
}

sealed class ValidationEvent {
    // a success object returninng a validation event
    object Success:ValidationEvent()
}
