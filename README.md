RealtimeWeather App

A simple and elegant Android weather application built with modern Android development tools. It allows users to search for any location and get the current weather conditions in real-time.


   ![RealtimeWeather App Screenshot](https://github.com/jassdahima/RealtimeWeather/blob/main/pictures/weatherpage1.png?raw=true)
   ![RealtimeWeather App Screenshot](https://github.com/jassdahima/RealtimeWeather/blob/main/pictures/weatherpage2.png?raw=true)                             ![RealtimeWeather App Screenshot](https://github.com/jassdahima/RealtimeWeather/blob/main/pictures/weatherpagedark.png?raw=true)
        
    


• Features

-   **Real-time Weather Data**: Fetches and displays up-to-date weather information.
-   **Global Search**: Search for any city or location worldwide.
-   **Dynamic UI**: The interface updates dynamically to show loading, success, and error states.
-   **Clean & Minimal Design**: A minimalist user interface built with Material Design 3.
-   **Detailed Information**: Displays primary data like temperature and condition, along with secondary details like humidity, wind speed, UV index, and more.

## Technologies & Architecture

This project is a demonstration of modern Android app development and utilizes the following technologies:

-   **Language**: [Kotlin](https://kotlinlang.org/)
-   **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetpack/compose) for building the UI declaratively.
-   **Architecture**: Model-View-ViewModel (MVVM) to separate the UI from the business logic.
-   **Networking**:
    -   [Retrofit](https://square.github.io/retrofit/): A type-safe HTTP client for making API requests.
    -   [Gson](https://github.com/google/gson): A library for converting JSON data into Kotlin objects.
-   **Asynchronous Programming**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for managing background threads and network calls.
-   **State Management**: `ViewModel` and `LiveData` from Android Jetpack to manage UI state in a lifecycle-aware way.
-   **Image Loading**: [Coil](https://coil-kt.github.io/coil/) for loading weather condition icons from the network efficiently.
-   **API**: [WeatherAPI.com](https://www.weatherapi.com/) for providing the weather data.

