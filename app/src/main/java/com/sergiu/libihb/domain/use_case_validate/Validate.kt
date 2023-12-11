package com.sergiu.libihb.domain.use_case_validate

// make this class inheritable with open keyword
interface Validate {
    // a function that checks if a inputed string is not blank and returning a ValidateResult
    // object according to the if statement
    fun inputNotBlank(inputType: String) :Boolean

    // a function that checks if the inputted string does qualify as an valid type (email or password)
    fun matchesRequiredType(inputType: String) :Boolean

    // the function that validates the input field if inserted value is ok
    // returns a validate result object
    fun validate(inputType: String) : ValidateResult


}