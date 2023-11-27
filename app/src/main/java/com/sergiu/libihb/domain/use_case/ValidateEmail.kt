package com.sergiu.libihb.domain.use_case

import android.util.Patterns


// by using the use-cases we follow the single responsibility principle
// SOLID
// implementing validate
class ValidateEmail : Validate{

//    TODO make val acces string res
    private val inputIsBlank = "The email can't be empty"
    private val doesNotMatchRequiredType = "The email is not valid"


    // check if email is not blank , and returns a boolean value
    override fun inputNotBlank(inputType: String) :Boolean{
        return inputType.isNotBlank()
    }

    // a function that checks if the inputted string does qualify as an valid email
    // use Patterns utils
    // and returns a boolean value
    override fun matchesRequiredType(inputType: String) : Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(inputType).matches()
    }

    // the function that validates the email if everything is ok
    // returns a validate result object
    override fun validate(inputType: String) :ValidateResult{
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


    // if the inputed email is valid check if it exists in FireBase
    fun inDataBase(inputType: String): ValidateResult {
        TODO("Not yet implemented")
    }

}
