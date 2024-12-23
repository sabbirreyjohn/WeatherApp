package xyz.androidrey.weatherapp.home.ui.home

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import live.studyquran.android.common.data.DataStoreRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import xyz.androidrey.multimoduletemplate.network.NetworkException
import xyz.androidrey.multimoduletemplate.network.NetworkResult
import xyz.androidrey.weatherapp.home.domain.entity.Condition
import xyz.androidrey.weatherapp.home.domain.entity.Current
import xyz.androidrey.weatherapp.home.domain.entity.CurrentData
import xyz.androidrey.weatherapp.home.domain.entity.Location
import xyz.androidrey.weatherapp.home.domain.repository.CurrentRepositoryImpl


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var repository: CurrentRepositoryImpl
    private lateinit var datastore: DataStoreRepository

    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var sampleCurrentData: CurrentData

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        datastore = mockk()
        viewModel = HomeViewModel(repository, datastore)
        sampleCurrentData = CurrentData(
            current = Current(
                last_updated_epoch = 1734921000.0,
                last_updated = "2024-12-23 08:30",
                temp_c = 17.5,
                temp_f = 63.6,
                is_day = 1.0,
                condition = Condition(
                    text = "Sunny",
                    icon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
                    code = 1000
                ),
                wind_mph = 6.7,
                wind_kph = 10.8,
                wind_degree = 13.0,
                wind_dir = "NNE",
                pressure_mb = 1015.0,
                pressure_in = 29.98,
                precip_mm = 0.0,
                precip_in = 0.0,
                humidity = 60.0,
                cloud = 0.0,
                feelslike_c = 17.5,
                feelslike_f = 63.6,
                windchill_c = 17.5,
                windchill_f = 63.6,
                heatindex_c = 17.5,
                heatindex_f = 63.6,
                dewpoint_c = 9.7,
                dewpoint_f = 49.4,
                vis_km = 10.0,
                vis_miles = 6.0,
                uv = 0.5,
                gust_mph = 10.6,
                gust_kph = 17.0
            ),
            location = Location(
                name = "Dhaka",
                region = "",
                country = "Bangladesh",
                lat = 23.7231,
                lon = 90.4086,
                tz_id = "Asia/Dhaka",
                localtime_epoch = 1734921869,
                localtime = "2024-12-23 08:44"
            )
        )
        coEvery { datastore.getLocation() } returns flowOf("Dhaka")
        coEvery { repository.getCurrentData("Dhaka") } returns NetworkResult.Success(
            sampleCurrentData
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch current data successfully when data is available in DataStore`() =
        runTest {
            //given
            coEvery { datastore.getLocation() } returns flowOf("Dhaka")
            coEvery { repository.getCurrentData("Dhaka") } returns NetworkResult.Success(
                sampleCurrentData
            )


            //when
            viewModel = HomeViewModel(repository, datastore)
            advanceUntilIdle()

            //then
            val currentState = viewModel.uiState.value
            assert(currentState is HomeUiState.CitySelected)


        }

    @Test
    fun `show no city selected when dataStore is empty`() = runTest {
        coEvery { datastore.getLocation() } returns flowOf("")

        viewModel = HomeViewModel(repository, datastore)
        advanceUntilIdle()

        val currentState = viewModel.uiState.value
        assert(currentState is HomeUiState.NoneSelected)
    }

    @Test
    fun `show error when data fetching failed from network`() = runTest {
        coEvery { datastore.getLocation() } returns flowOf("Dhaka")
        coEvery { repository.getCurrentData("Dhaka") } returns NetworkResult.Error(
            "Network Error",
            NetworkException.UnknownException("", Exception())
        )

        viewModel = HomeViewModel(repository, datastore)
        advanceUntilIdle()

        val currentState = viewModel.uiState.value
        assert(currentState is HomeUiState.Error)

    }

    @Test
    fun `fetch current data manually and successfully completes`() = runTest {
        coEvery { datastore.getLocation() } returns flowOf("Dhaka")
        coEvery { repository.getCurrentData("Dhaka") } returns NetworkResult.Success(
            sampleCurrentData
        )

        viewModel = HomeViewModel(repository, datastore)
        viewModel.getCurrentData("Dhaka")
        advanceUntilIdle()

        val currentState = viewModel.uiState.value
        assert(currentState is HomeUiState.SearchSuccess)
    }
}