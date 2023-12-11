package com.sergiu.libihb.presentation.events


// a class containing all posible events that belong to one
// action done by the user on register page, ex: submit button pressed
sealed class RegisterFormEvent {

    // use an object(singleton) - simple event
    object SubmitClicked: RegisterFormEvent()

    // use data class - the event needs more data
    data class PasswordChanged(val password:String) : RegisterFormEvent()
    data class EmailChanged(val email:String) : RegisterFormEvent()
    data class NameChanged(val name:String) : RegisterFormEvent()
    data class PhoneChanged(val phone:String) : RegisterFormEvent()
}