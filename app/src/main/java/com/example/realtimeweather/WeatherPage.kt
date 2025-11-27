package com.example.realtimeweather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.realtimeweather.api.NetworkResponse
import com.example.realtimeweather.api.WeatherModel



@Composable
fun WeatherPage(viewModel: WeatherViewModel){
    var city by remember { mutableStateOf("") }

    val weatherResult = viewModel.weatherResult.observeAsState()


    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(vertical = 40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                   ,
                value = city ,onValueChange = {
                city = it
            },
                label = {
                    Text(text = "Search for any location")
                },
                shape = RoundedCornerShape(30.dp),
                trailingIcon = {
                    IconButton(onClick = {viewModel.getData(city)}) {
                        Icon(imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.LocationOn,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.DarkGray,
                    focusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
                    unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Black,
                    ), maxLines = 1)
        }

        when(val result = weatherResult.value){
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }
            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                WeatherDetails(data = result.data)
            }
            null -> {}
        }
    }
}


@Composable
fun WeatherDetails(data : WeatherModel){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                fontSize = 32.sp, fontWeight = FontWeight.Bold,
                color = Color.Black,
            )){
                append(data.location.name)
            }
            append(",")
            withStyle(style = SpanStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )){
                append(data.location.country)
            }

        },textAlign = TextAlign.Center,
             lineHeight = 40.sp,
            letterSpacing = 2.sp,
            modifier = Modifier.padding(horizontal = 16.dp))


        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "${data.current.temp_c}°c ",
            fontSize = 72.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        AsyncImage(
            modifier = Modifier.size(160.dp),
            model = "https:${data.current.condition.icon}".replace("64x64","128x128"),
            contentDescription = "Condition icon "
        )
        Text(
            text = data.current.condition.text,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(40.dp))
        Card(
            modifier = Modifier
            .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherKeyVal("Humidity",data.current.humidity)
                    WeatherKeyVal("Wind Speed",data.current.wind_kph+" km/h")
                }
                Spacer(modifier = Modifier.height(24.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherKeyVal("UV",data.current.uv)
                    WeatherKeyVal("Percipation",data.current.precip_mm+" mm")
                }

                Spacer(modifier = Modifier.height(24.dp))


                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherKeyVal("Local Time",data.location.localtime.split(" ")[1])
                    WeatherKeyVal("Local Date",data.location.localtime.split(" ")[0])
                }
            }
        }
    }
}

@Composable
fun WeatherKeyVal(key : String,value : String){
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = value, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = key, fontWeight = FontWeight.SemiBold, color = Color.Gray)
    }
}