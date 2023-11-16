package com.sergiu.libihb.domain.use_case

data class ValidateResult(
    val isValid : Boolean,
    val messageIfNotValid: String? = null
)