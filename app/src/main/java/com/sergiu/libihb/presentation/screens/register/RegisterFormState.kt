package com.sergiu.libihb.presentation.screens.register


data class RegisterFormState (
    val password: String = "",
    val email: String = "",
    val name: String ="",
    val phone: String ="",
    val passwordError: String? = null,
    val emailError: String? = null,
    val nameError: String? = null,
    val phoneError: String? = null,
)