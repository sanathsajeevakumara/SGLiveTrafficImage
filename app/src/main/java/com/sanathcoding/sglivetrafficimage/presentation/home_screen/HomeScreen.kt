package com.sanathcoding.sglivetrafficimage.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.sanathcoding.sglivetrafficimage.core.navigation.BottomNavBar
import com.sanathcoding.sglivetrafficimage.presentation.home_screen.screen.CameraListScreen
import com.sanathcoding.sglivetrafficimage.presentation.home_screen.screen.MapScreen

@Composable
fun NewHome(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0)}

    Scaffold(
        bottomBar = { BottomNavBar {selectedItem = it} }
    ) { paddingValue ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            when (selectedItem){
                0 -> MapScreen()
                1 -> CameraListScreen(navController = navController)
            }
        }
    }
}
