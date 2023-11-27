package com.sergiu.libihb.presentation.events


// a class containing all posible events that belong to one
// action done by the user on log in page , ex: submit button pressed
sealed class LogInFormEvent {
    // use an object(singleton) - simple event
    object SubmitClicked: LogInFormEvent()

    // use data class - the event needs more data
    data class PasswordChanged(val password:String) : LogInFormEvent()
    data class EmailChanged(val email:String) : LogInFormEvent()
}