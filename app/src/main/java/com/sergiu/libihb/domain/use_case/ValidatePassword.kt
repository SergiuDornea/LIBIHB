package com.sergiu.libihb.domain.use_case


// by using the use-cases we follow the single responsibility principle
// SOLID
// implementing  validate
class ValidatePassword : Validate{

    // const val to chage easy if needed
    private val minPasswordLenght : Int = 6

//    TODO make val acces string res
    private val inputIsBlank = "The password can't be empty"
    private val notComplex = "The password must contain: letters (both lowercase and uppercase) and digits"
    private val doesNotMatchRequiredType = "The password must be over $minPasswordLenght characters long "

    // check if password is not blank , and returns a boolean value
    override fun inputNotBlank(inputType: String) :Boolean{
        return inputType.isBlank()
    }

    // a function that checks if the inputted string does qualify as an valid password
    // must be over 6 characters long
    // and returns a boolean value
    override fun matchesRequiredType(inputType: String) : Boolean{
        return inputType.length > 5
    }


    // a  function that checks if at least one character in the password
    // is a digit and at least one character is a letter
    // use any function to check
    fun isPasswordComplex(inputType: String):Boolean{
        return inputType.any{ it.isLetter()}
                && inputType.any{it.isUpperCase()}
                && inputType.any{it.isDigit()}

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
        if(!isPasswordComplex(inputType)){
            return ValidateResult(
                isValid = false,
                messageIfNotValid = notComplex
            )
        }
        return ValidateResult(
            isValid = true
        )

    }

}






