package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.MapViewModel

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color,
    isFavorite: Boolean
) {

    Surface(
        shape = CircleShape,
        modifier = Modifier
            .size(32.dp),
        color = Color(0x77000000)
    ) {

        var favoriteClicked by remember { mutableStateOf(false) }
//        var favoriteClicked by remember {
//            mutableStateOf(isFavorite)
//        }

        IconToggleButton(
            checked = favoriteClicked,
            onCheckedChange = {
                favoriteClicked = !favoriteClicked
            }
        ) {
            Icon(
                tint = color,
                modifier = modifier.graphicsLayer {
                    scaleX = 1.3f
                    scaleY = 1.3f
                },
                imageVector = if (favoriteClicked) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = "Favorite Button"
            )
        }
    }


}