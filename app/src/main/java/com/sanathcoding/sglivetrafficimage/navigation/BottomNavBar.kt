package com.sanathcoding.sglivetrafficimage.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.runtime.Composable

@Composable
fun BottomNavBar(
    onSelectedItem: (Int) -> Unit
) {
    BottomNavigation {
        BottomNavigationItem(
            selected = true,
            onClick = { onSelectedItem(0) },
            icon = { Icon(imageVector = Icons.Filled.Map, contentDescription = "Map Icon") },
            enabled = true,
        )
        BottomNavigationItem(
            selected = true,
            onClick = { onSelectedItem(1) },
            icon = { Icon(imageVector = Icons.Filled.List, contentDescription = "List Icon") },
            enabled = true,
        )
    }
}