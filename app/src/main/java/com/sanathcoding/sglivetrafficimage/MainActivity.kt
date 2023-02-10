package com.sanathcoding.sglivetrafficimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.sanathcoding.sglivetrafficimage.core.navigation.SetupNavGraph
import com.sanathcoding.sglivetrafficimage.ui.theme.SGLiveTrafficImageTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SGLiveTrafficImageTheme {
                SetupNavGraph(navController = rememberNavController())
            }
        }
    }
}