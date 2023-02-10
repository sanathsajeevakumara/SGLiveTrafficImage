package com.sanathcoding.sglivetrafficimage.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sanathcoding.sglivetrafficimage.navigation.destination.HomeScreenDestination
import com.sanathcoding.sglivetrafficimage.navigation.graphs.HomeNavGraph

@Composable
fun HomeScreen(
    bottomNavController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(bottomNavController)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HomeNavGraph(navController = bottomNavController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    val screenList = listOf(
        HomeScreenDestination.MapScreen,
        HomeScreenDestination.CameraListScreen
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val bottomBarDestination = screenList.any { bottomNavScreen ->
        bottomNavScreen.route == currentDestination?.route
    }

    if (bottomBarDestination) {
        BottomNavigation {
            screenList.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }


}

@Composable
fun RowScope.AddItem(
    screen: HomeScreenDestination,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.name)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}