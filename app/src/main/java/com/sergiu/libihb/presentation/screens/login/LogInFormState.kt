package com.sergiu.libihb.presentation.screens.login


// the state of the values when the user opens the screen
data class LogInFormState(
    val password: String? = null,
    val email: String? = null,
    val passwordError: String? = null,
    val emailError: String? = null
)
