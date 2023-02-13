package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sanathcoding.sglivetrafficimage.R
import com.sanathcoding.sglivetrafficimage.core.navigation.Screen
import com.sanathcoding.sglivetrafficimage.core.util.UiText
import com.sanathcoding.sglivetrafficimage.core.util.showToast
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen.component.TrafficCameraList
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.MapViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
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
    val cameraList by viewModel.cameraList.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

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

            TextField(
                value = searchQuery,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Search") }
            )
            Spacer(modifier = Modifier.height(16.dp))

//            if(isSearching) {
//                Box(modifier = Modifier.fillMaxSize()) {
//                    CircularProgressIndicator(
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//            } else
                LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(cameraList.size) { i ->
                    TrafficCameraList(
                        camera = cameraList[i],
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