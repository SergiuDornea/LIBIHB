package com.sergiu.libihb.domain.use_case_validate

data class ValidateResult(
    val isValid : Boolean,
    val messageIfNotValid: String? = null
)