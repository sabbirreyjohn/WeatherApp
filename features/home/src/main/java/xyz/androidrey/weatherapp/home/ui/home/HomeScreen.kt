package xyz.androidrey.weatherapp.home.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import xyz.androidrey.weatherapp.home.R
import xyz.androidrey.weatherapp.home.domain.entity.CurrentData


@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()
    var query by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 64.dp, 16.dp, 16.dp)

    ) {
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = {
                focusManager.clearFocus() // Close the keyboard
                viewModel.getCurrentData(query) // Perform your search logic here
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DashboardStateHandler(
            uiState,
            searchSuccess = {
                SearchResultCard(it) {
                    viewModel.citySelected(it)
                }
            },
            citySelected = {
                WeatherDetailsCard(it)
            }
        )
    }
}

@Composable
fun SearchResultCard(data: CurrentData, citySelected: (CurrentData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .clickable {
                citySelected(data)
            },
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = data.location.name, fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Text(
                        text = "${data.current.temp_c}", fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(R.string.degree), fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            AsyncImage(
                model = "https:${data.current.condition.icon.replace("64x64", "128x128")}",
                contentDescription = "Sun",
                modifier = Modifier.size(128.dp)
            )
        }

    }
}

@Composable
fun WeatherDetailsCard(data: CurrentData) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {

        AsyncImage(
            model = "https:${data.current.condition.icon.replace("64x64", "128x128")}",
            contentDescription = "Sun",
            modifier = Modifier.size(128.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = data.location.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(R.drawable.arrow),
                contentDescription = "Arrow",
                modifier = Modifier.size(16.dp)
            )
        }
        Row {
            Text(
                text = "${data.current.temp_c}",
                fontSize = 64.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = stringResource(R.string.degree),
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                WeatherDataPoint(stringResource(R.string.humidity), "${data.current.humidity}%")
                WeatherDataPoint(stringResource(R.string.uv), "${data.current.uv}")
                WeatherDataPoint(
                    stringResource(R.string.feels_like),
                    "${data.current.feelslike_c}°"
                )
            }
        }
    }
}

@Composable
fun WeatherDataPoint(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
        Text(text = value, fontSize = 16.sp)
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Search Location", color = Color.Gray) },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search Icon",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp),
                tint = Color.Gray
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Gray,

            )
    )
}




