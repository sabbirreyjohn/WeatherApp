package xyz.androidrey.weatherapp.home.ui.home


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import xyz.androidrey.weatherapp.home.domain.repository.FakeDataStoreRepository
import xyz.androidrey.weatherapp.home.domain.repository.FakeRepository
import xyz.androidrey.weatherapp.home.domain.util.mockCurrentData

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()



    private lateinit var viewModel : HomeViewModel
    private lateinit var navController : TestNavHostController
    @Before
    fun setUp(){
        viewModel = HomeViewModel(FakeRepository(), FakeDataStoreRepository())
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun searchBar_isDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composeTestRule.onNodeWithText("Search Location").assertIsDisplayed()
    }

    @Test
    fun dashboard_showsSearchResult() {
        // Simulate search success
        viewModel.updateUiState(HomeUiState.SearchSuccess(mockCurrentData))



        composeTestRule.setContent {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Verify SearchResultCard is displayed
        composeTestRule.onNodeWithText("Dhaka").assertIsDisplayed()
        composeTestRule.onNodeWithText("17.5").assertIsDisplayed()
    }

    @Test
    fun weatherDetailsCard_displaysCorrectData() {
        // Simulate city selected state
        viewModel.updateUiState(HomeUiState.SearchSuccess(mockCurrentData))

        composeTestRule.setContent {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Verify weather details are displayed
        composeTestRule.onNodeWithText("Dhaka").assertIsDisplayed()
        composeTestRule.onNodeWithText("17.5").assertIsDisplayed()
    }
}