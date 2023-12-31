package com.example.myica.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myica.data.api.model.Force
import com.example.myica.ui.MainDestinations
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {

    val state by homeViewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {


        LazyColumn(state = listState) {
            items(state){force: Force ->
                PoliceForceCard(navController,force = force)
            }
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            coroutineScope.launch {
                navController?.navigate(MainDestinations.SEARCH_ROUTE)
            }
        },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)) { // Add some padding at the bottom
            Text("All Crimes")
        }
    }

    Spacer(Modifier.height(20.dp))
}


@Composable
fun PoliceForceCard(navController: NavController,force: Force) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    )
    {
        Column(
            modifier = Modifier.padding(15.dp)
        )
        {
            Text(text = "Id: ${force.id}",
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                            navController?.navigate(MainDestinations.SEARCH_ROUTE)
                    },
            )
            Text(text = "Force Name:${force.name}")
        }
    }
}
