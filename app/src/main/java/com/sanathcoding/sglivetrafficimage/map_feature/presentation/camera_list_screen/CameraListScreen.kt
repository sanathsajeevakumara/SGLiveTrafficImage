package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen.component.TrafficCameraList
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.MapEvent
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.MapViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun CameraListScreen(
    navController: NavHostController,
    viewModel: MapViewModel = hiltViewModel(),
) {
    val state = viewModel.mapState
    val scaffoldState = rememberScaffoldState()

    val searchQuery by viewModel.searchQuery.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val cameraList by viewModel.cameraList.collectAsState()

    val pickerDate by remember { mutableStateOf(LocalDate.now()) }
    val pickerTime by remember { mutableStateOf(LocalTime.now()) }

    val formattedDate by remember {
        derivedStateOf { DateTimeFormatter.ofPattern("yyyy-MM-dd").format(pickerDate) }
    }

    val formattedTime by remember {
        derivedStateOf { DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(pickerTime) }
    }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                dateDialogState.show()
            }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Filter By Date")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = viewModel::onSearchTextChange,
                    modifier = Modifier.width(screenWidth * 0.8f),
                    placeholder = { Text(text = "Search") }
                )
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Order By",
                    modifier = Modifier
                        .width(screenWidth * 0.2f)
                        .clickable {
                            viewModel.onEvent(MapEvent.OnFilterButtonClicked)
                        }
                        .align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            if(isSearching) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {

//                state.camera?.let { cameraList ->
//                    items(cameraList) { camera ->
//                        TrafficCameraList(
//                            camera = camera,
//                            isFavorite = state.isFavorite,
//                            navController
//                        )
//                    }
//                }

                Log.d("Screen", "CameraList size: ${cameraList.size}")
                items(cameraList) { camera ->
                    TrafficCameraList(
                        camera = camera,
                        isFavorite = state.isFavorite,
                        navController
                    )
                }

            }
        }

        ShowDatePicker(dateDialogState, timeDialogState, pickerDate)

        ShowTimePicker(timeDialogState, viewModel, formattedDate, formattedTime, pickerTime)

    }
}

@Composable
private fun ShowTimePicker(
    timeDialogState: MaterialDialogState,
    viewModel: MapViewModel,
    formattedDate: String,
    formattedTime: String,
    pickerTime: LocalTime?
) {
    var pickerTime1 = pickerTime
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton("Ok") {
                viewModel.getTrafficImageByDateTime(formattedDate, formattedTime)
            }
            negativeButton("Cancel")
        }
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a time",
        ) {
            pickerTime1 = it
        }
    }
}

@Composable
private fun ShowDatePicker(
    dateDialogState: MaterialDialogState,
    timeDialogState: MaterialDialogState,
    pickerDate: LocalDate?
) {
    var pickerDate1 = pickerDate
    MaterialDialog(
        dialogState = dateDialogState,
        onCloseRequest = {},
        buttons = {
            positiveButton("Ok") {
                timeDialogState.show()
            }
            negativeButton("Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
        ) {
            pickerDate1 = it
        }
    }
}