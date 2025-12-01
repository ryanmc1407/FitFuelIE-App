 package com.fitfuelie.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fitfuelie.app.navigation.Screen
import com.fitfuelie.app.ui.onboarding.OnboardingScreen
import com.fitfuelie.app.ui.onboarding.OnboardingViewModel
import com.fitfuelie.app.ui.dashboard.DashboardScreen

@Composable
fun FitFuelIEApp(viewModel: OnboardingViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    // Check if onboarding is complete
    val isOnboardingComplete by viewModel.isOnboardingComplete().collectAsState(initial = false)

    val startDestination = if (isOnboardingComplete) Screen.Dashboard.route else Screen.Onboarding.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onOnboardingComplete = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
    }
}
