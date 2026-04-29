package com.example.modul3compose

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: ComicViewModel
){
    val listComics by viewModel.listComics.collectAsStateWithLifecycle()
    val carouselComics by viewModel.carouselComics.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text(text = "MyComicList", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)},
                actions = {
                    IconButton(onClick = {navController.navigate("setting") }) {
                        Icon(Icons.Default.Menu, contentDescription = "settings")
                    }
                }
            )
        }
    ) {innerPadding ->
        val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                RecommendationCarousel(carouselComics, isLandscape, navController, viewModel)
            }

            item {Text(text = "Top Comic",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 30.dp, bottom = 5.dp, start = 20.dp)
            )
            }

            items(listComics) {item ->
                ComicList(item, isLandscape, navController, viewModel)
            }
        }
    }
}

@Composable
fun DetailsScreen(viewModel: ComicViewModel){
    val selectedComic by viewModel.selectedComic.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {innerPadding ->
        selectedComic?.let { comic ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text(text = comic.title)

                Image(
                    painter = painterResource(comic.coverImage),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )

                Text(text = comic.description)
            }
        } ?: run {
            Text("Comic not found")
        }
    }
}

@Composable
fun SettingsScreen(){}
