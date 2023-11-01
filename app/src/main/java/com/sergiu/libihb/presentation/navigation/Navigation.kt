package com.example.libihb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.libihb.screens.about.AboutUsScreen
import com.example.libihb.screens.contact.ContactUsScreen
import com.example.libihb.screens.home.HomeScreen
import com.example.libihb.screens.log.LogInScreen
import com.example.libihb.screens.log.LogOutScreen
import com.example.libihb.screens.main.MainScreen
import com.example.libihb.screens.settings.SettingsScreen
import com.example.libihb.screens.share.ShareScreen

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