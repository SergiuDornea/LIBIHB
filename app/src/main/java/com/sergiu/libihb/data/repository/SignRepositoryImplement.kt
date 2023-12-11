package com.sergiu.libihb.data.repository

import com.google.firebase.auth.FirebaseUser
import com.sergiu.libihb.domain.repository.SignRepository

class SignRepositoryImplement : SignRepository {
    override val currentUser: FirebaseUser?
        get() = TODO("Not yet implemented")

    override suspend fun signIn(
        name: String,
        email: String,
        password: String,
        phone: String

    ): Result<FirebaseUser> {
        TODO("Not yet implemented")
    }

    override suspend fun logIn(email: String, password: String): Result<FirebaseUser> {
        TODO("Not yet implemented")
    }

    override fun logOut() {
        TODO("Not yet implemented")
    }
}