package xyz.androidrey.weatherapp.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import live.studyquran.android.common.domain.repository.DataStoreRepository
import xyz.androidrey.multimoduletemplate.network.NetworkResult
import xyz.androidrey.weatherapp.home.domain.repository.CurrentRepository
import xyz.androidrey.weatherapp.home.domain.entity.CurrentData
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val repo: CurrentRepository?,
    private val dataStoreRepository: DataStoreRepository?
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.NoneSelected)
    open val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            val cityName = dataStoreRepository?.getLocation()?.first()
            if (cityName!!.isNotEmpty()) {
                _uiState.value = HomeUiState.Searching
                when (val status = repo?.getCurrentData(cityName)) {
                    is NetworkResult.Error -> {
                        _uiState.value = HomeUiState.Error(status.exception.message!!)
                    }

                    is NetworkResult.Success -> {
                        _uiState.value = HomeUiState.CitySelected(status.result)
                    }

                    null -> {

                    }
                }
            }
        }
    }

    fun updateUiState(state: HomeUiState) {
        _uiState.value = state
    }


    fun getCurrentData(query: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Searching
            when (val status = repo?.getCurrentData(query)) {
                is NetworkResult.Error -> {
                    _uiState.value = HomeUiState.Error(status.exception.message!!)
                }

                is NetworkResult.Success -> {
                    _uiState.value = HomeUiState.SearchSuccess(status.result)
                }

                null -> {

                }
            }
        }
    }

    fun citySelected(data: CurrentData) {
        viewModelScope.launch {
            dataStoreRepository?.setLocation(data.location.name)
            _uiState.value = HomeUiState.CitySelected(data)
        }
    }
}