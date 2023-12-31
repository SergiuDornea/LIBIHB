package com.sergiu.libihb.domain.use_case_validate


// by using the use-cases we follow the single responsibility principle
// SOLID
// implementing  validate
class ValidatePassword : Validate{

    // const val to chage easy if needed
    private val minPasswordLength : Int = 6

//    TODO make val acces string res
    private val inputIsBlank = "The password can't be empty"
    private val notComplex = "The password must include: lowercase and uppercase letters, and digits"
    private val doesNotMatchRequiredType = "The password must be over $minPasswordLength characters long "

    // check if password is not blank , and returns a boolean value
    override fun inputNotBlank(inputType: String) :Boolean{
        return inputType.isNotBlank()
    }

    // a function that checks if the inputted string does qualify as an valid password
    // must be over 6 characters long
    // and returns a boolean value
    override fun matchesRequiredType(inputType: String) : Boolean{
        return inputType.length >= minPasswordLength
    }


    // a  function that checks if at least one character in the password
    // is a digit and at least one character is a letter
    // use any function to check
    fun isPasswordComplex(inputType: String):Boolean{
        return inputType.any{ it.isLetter()}
                && inputType.any{it.isUpperCase()}
                && inputType.any{it.isDigit()}

    }


    // the function that validates the password if everything is ok
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


    // if the inputed email is valid check if it exists in FireBase
    fun inDataBase(inputType: String): ValidateResult {
        TODO("Not yet implemented")
    }

}






