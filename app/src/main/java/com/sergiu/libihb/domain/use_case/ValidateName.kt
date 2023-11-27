package com.sergiu.libihb.domain.use_case

class ValidateName:Validate {
    // const val to change easy if needed
    private val minNameLength : Int = 2
    private val inputIsBlank = "The name can't be empty"
    private val doesNotMatchRequiredType = "The name must include at least two characters"

    // check if name is not blank , and returns a boolean value
    override fun inputNotBlank(inputType: String) :Boolean{
        return inputType.isNotBlank()
    }

    // a function that checks if the inputted string does qualify as an valid name
    // a valid name must be at least 2 characters long
    override fun matchesRequiredType(inputType: String): Boolean {
        return inputType.length >= minNameLength
    }

    override fun validate(inputType: String): ValidateResult {
        if (!inputNotBlank(inputType)){
            return ValidateResult(
                isValid = false,
                messageIfNotValid = inputIsBlank
            )
        }
        if (!matchesRequiredType(inputType)){
            return ValidateResult(
                isValid = false,
                messageIfNotValid = doesNotMatchRequiredType
            )
        }
        return ValidateResult(
            isValid = true
        )

    }
}