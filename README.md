# SGLiveTrafficImage
SG Live Traffic Image Application

## Technologies

* Android Studio Version: Android Studio Electric Eel | 2022.1.1
* Android Archiecture: Clean MVVM
* Observables : Kotlin Flow
* Composable state management: ViewModel
* Caching: RoomDB, Data Store
* Consume REST API: Retrofit with Kotlin Coroutines
* Dependency Injection: Dagger Hilt
* UI: Jetpack Compose

### Architecture Overview

![Untitled drawio](https://user-images.githubusercontent.com/11756630/218929374-aafc54c8-16fe-4bac-989d-877ca3c9f33b.png)

#### Note
Image will not appiear when user clcik the marker in Google map for the 1st time. after some time it will appear again when clicking by the same marker in Google map - https://stackoverflow.com/questions/39551774/picasso-image-not-loading-in-custom-infowindow-why
