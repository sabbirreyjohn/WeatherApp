package xyz.androidrey.weatherapp.home.domain.util

import xyz.androidrey.weatherapp.home.domain.entity.Condition
import xyz.androidrey.weatherapp.home.domain.entity.Current
import xyz.androidrey.weatherapp.home.domain.entity.CurrentData
import xyz.androidrey.weatherapp.home.domain.entity.Location

val mockCurrentData=  CurrentData(
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