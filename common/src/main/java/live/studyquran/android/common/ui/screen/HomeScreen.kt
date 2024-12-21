package live.studyquran.android.common.ui.screen

import kotlinx.serialization.Serializable

@Serializable
sealed class HomeScreen() {
    @Serializable
    data object Main : HomeScreen()
}