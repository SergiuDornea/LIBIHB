package com.sergiu.libihb.presentation.screens.logscreens.`in`


// the state of the values when the user opens the screen
data class LogInFormState(
    val password: String,
    val email: String,
    val passwordError: String? = null,
    val emailError: String? = null
)
