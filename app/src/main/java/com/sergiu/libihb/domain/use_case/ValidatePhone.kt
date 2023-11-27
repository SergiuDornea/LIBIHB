package com.sergiu.libihb.domain.use_case

import android.util.Patterns

class ValidatePhone: Validate{
    // a val that holds the required lenght of the phone number
    private val reqLength :Int = 10

    private val inputIsBlank = "The phone number can't be empty"
    private val doesNotMatchRequiredType = "The phone number is not valid"
    private val doesNotHeaveReqLength = "The phone number must have 10 digits"
    override fun inputNotBlank(inputType: String): Boolean {
        return inputType.isNotBlank()
    }


    // a function that checks if the inputted string does qualify as an valid phone number
    // use Patterns utils
    // and returns a boolean value
    override fun matchesRequiredType(inputType: String): Boolean {
        return Patterns.PHONE.matcher(inputType).matches()
    }

//    a function that checks if the phone number has a lenght of 10 digits
    fun hasCorrectLength(inputType: String):Boolean{
        return reqLength == inputType.length
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

        if(!hasCorrectLength(inputType)){
            return ValidateResult(
                isValid = false,
                messageIfNotValid = doesNotHeaveReqLength
            )
        }
        return ValidateResult(
            isValid = true
        )
    }


}