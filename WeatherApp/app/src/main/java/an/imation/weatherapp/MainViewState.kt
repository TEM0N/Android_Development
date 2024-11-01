package an.imation.weatherapp

import an.imation.weatherapp.api.WeatherResponse

sealed interface MainViewState

data object Loading:MainViewState
data class Data(val weatherResponse: WeatherResponse): MainViewState
data object Error:MainViewState