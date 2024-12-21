package xyz.androidrey.weatherapp.home.ui.home

import androidx.compose.runtime.Composable
import xyz.androidrey.multimoduletemplate.theme.components.ErrorScreen
import xyz.androidrey.multimoduletemplate.theme.components.InfoScreen
import xyz.androidrey.multimoduletemplate.theme.components.LoadingScreen
import xyz.androidrey.weatherapp.home.domain.entity.CurrentData

@Composable
fun DashboardStateHandler(
    state: HomeUiState,
    searching: @Composable () -> Unit = { LoadingScreen() },
    error: @Composable (String) -> Unit = { ErrorScreen(it) },
    searchSuccess: @Composable (CurrentData) -> Unit,
    citySelected: @Composable (CurrentData) -> Unit,
) {
    when (state) {
        is HomeUiState.Error -> error(state.message)
        is HomeUiState.NoneSelected -> InfoScreen("No City Selected", "Please Search For A City")
        is HomeUiState.Searching -> searching()
        is HomeUiState.CitySelected -> citySelected(state.currentData)
        is HomeUiState.SearchSuccess -> searchSuccess(state.currentData)
    }
}