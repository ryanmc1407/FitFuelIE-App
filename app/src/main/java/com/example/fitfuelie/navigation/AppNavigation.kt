package com.example.fitfuelie.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitfuelie.di.AppContainer
import com.example.fitfuelie.ui.screens.*
import com.example.fitfuelie.ui.viewmodel.*

@Composable
fun AppNavigation(
    appContainer: AppContainer,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = modifier
    ) {
        composable(Screen.Dashboard.route) {
            val viewModel = DashboardViewModel(
                appContainer.mealRepository,
                appContainer.trainingSessionRepository,
                appContainer.userProfileRepository
            )

            DashboardScreen(
                viewModel = viewModel,
                onAddMeal = { navController.navigate(Screen.MealPlanner.route) },
                onAddTraining = { navController.navigate(Screen.TrainingCalendar.route) },
                onViewMeals = { navController.navigate(Screen.MealPlanner.route) },
                onViewTraining = { navController.navigate(Screen.TrainingCalendar.route) }
            )
        }

        composable(Screen.MealPlanner.route) {
            val viewModel = MealPlannerViewModel(appContainer.mealRepository)

            MealPlannerScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.TrainingCalendar.route) {
            val viewModel = TrainingCalendarViewModel(appContainer.trainingSessionRepository)

            TrainingCalendarScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.GroceryList.route) {
            val viewModel = GroceryListViewModel(appContainer.groceryItemRepository)

            GroceryListScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Profile.route) {
            val viewModel = ProfileViewModel(appContainer.userProfileRepository)

            ProfileScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Home", Icons.Default.Home)
    object MealPlanner : Screen("meal_planner", "Meals", Icons.Default.Restaurant)
    object TrainingCalendar : Screen("training_calendar", "Training", Icons.Default.FitnessCenter)
    object GroceryList : Screen("grocery_list", "Grocery", Icons.Default.ShoppingCart)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
}
