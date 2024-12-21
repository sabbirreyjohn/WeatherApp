package xyz.androidrey.weatherapp.home.ui.home

import xyz.androidrey.weatherapp.home.domain.entity.CurrentData

sealed class HomeUiState {
    data object NoneSelected : HomeUiState()
    data object Searching : HomeUiState()
    data class Error(val message: String) : HomeUiState()
    data class SearchSuccess(val currentData: CurrentData) : HomeUiState()
    data class CitySelected(val currentData: CurrentData) : HomeUiState()
}