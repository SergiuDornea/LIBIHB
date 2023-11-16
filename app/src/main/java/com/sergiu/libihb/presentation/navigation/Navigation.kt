package com.sergiu.libihb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sergiu.libihb.presentation.screens.about.AboutUsScreen
import com.sergiu.libihb.presentation.screens.contact.ContactUsScreen
import com.sergiu.libihb.presentation.screens.home.HomeScreen
import com.sergiu.libihb.presentation.screens.logout.LogOutScreen
import com.sergiu.libihb.presentation.screens.main.MainScreen
import com.sergiu.libihb.presentation.screens.settings.SettingsScreen
import com.sergiu.libihb.presentation.screens.share.ShareScreen
import com.sergiu.libihb.presentation.screens.login.LogInScreen

@Composable
fun LIBIHBNavigation(){
    //  tracks the  backstack and screens state
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = AppScreens.LogInScreen.name){
        // here is where I create the graph
        composable(AppScreens.AboutUsScreen.name){
            // pass the navaController to allow the user, if needed, to go to another screen
            AboutUsScreen(navController = navController)
        }

        composable(AppScreens.ContactUsScreen.name){
            // pass the navaController to allow the user, if needed, to go to another screen
            ContactUsScreen(navController = navController)
        }

        composable(AppScreens.HomeScreen.name){
            // pass the navaController to allow the user, if needed, to go to another screen
            HomeScreen(navController = navController)
        }

        composable(AppScreens.LogInScreen.name){
            // pass the navaController to allow the user, if needed, to go to another screen
            LogInScreen(navController = navController)
        }

        composable(AppScreens.LogOutScreen.name){
            // pass the navaController to allow the user, if needed, to go to another screen
            LogOutScreen(navController = navController)
        }

        composable(AppScreens.MainScreen.name){
            // pass the navaController to allow the user, if needed, to go to another screen
            MainScreen(navController = navController)
        }

        composable(AppScreens.SettingsScreen.name){
            // pass the navaController to allow the user, if needed, to go to another screen
            SettingsScreen(navController = navController)
        }

        composable(AppScreens.ShareScreen.name){
            // pass the navaController to allow the user, if needed, to go to another screen
            ShareScreen(navController = navController)
        }
    }
}