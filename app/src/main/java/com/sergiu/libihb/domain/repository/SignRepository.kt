package com.sergiu.libihb.domain.repository

import com.google.firebase.auth.FirebaseUser

interface SignRepository{
    // the current user
    val currentUser:FirebaseUser?
    // a function to register the user into firebase
    suspend fun signIn(name: String, email:String, password:String, phone:String):Result<FirebaseUser>
   // a function to allow the user to log into the fire base account
    suspend fun logIn(email:String, password:String):Result<FirebaseUser>
    // log out function
    fun logOut()
}