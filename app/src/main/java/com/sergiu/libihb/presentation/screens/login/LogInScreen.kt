package com.sergiu.libihb.presentation.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sergiu.libihb.presentation.components.LogForm
import com.sergiu.libihb.presentation.components.Logo

@Composable
fun LogInScreen(navController: NavController){
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // add the logo
            Logo()
            LogForm()

        }
    }
}


