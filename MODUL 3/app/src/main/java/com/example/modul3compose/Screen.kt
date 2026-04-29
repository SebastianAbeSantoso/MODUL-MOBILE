package com.example.modul3compose

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: ComicViewModel,
    isLandscape: Boolean
){
    val listComics by viewModel.listComics.collectAsStateWithLifecycle()
    val carouselComics by viewModel.carouselComics.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text(text = "MyComicList", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)},
                actions = {
                    IconButton(onClick = {navController.navigate("settings") }) {
                        Icon(Icons.Default.Menu, contentDescription = "settings")
                    }
                }
            )
        }
    ) {innerPadding ->

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
fun DetailsScreen(viewModel: ComicViewModel, navController: NavHostController, isLandscape: Boolean){
    val selectedComic by viewModel.selectedComic.collectAsStateWithLifecycle()
    val descFontSize =  if (isLandscape) 24.sp else 20.sp
    val descMaxLine = if (isLandscape) 3 else 15

    val titleFontSize = if (isLandscape) 32.sp else 40.sp
    val titleMaxLine = if (isLandscape) 1 else 10
    val titleLineHeight = 50.sp

    val authorFontSize = if (isLandscape) 24.sp else 20.sp
    val authorMaxLine = if (isLandscape) 1 else 3

    val genreFontSize = 20.sp
    val genreMaxLine = if (isLandscape) 1 else 3

    val buttonFontSize = if (isLandscape) 30.sp else 20.sp
    val cardHeight = if (isLandscape) 0.75f else 0.89f

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {innerPadding ->
        selectedComic?.let { comic ->
            Box(modifier = Modifier.fillMaxSize()){
                Image(painter = painterResource(comic.backgroundImage),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = "carousel_bg_image")

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.2f),
                                Color.Black.copy(alpha = 0.9f)
                            )
                        )
                    )
                )
                Column (modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize())
                {
                    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(cardHeight)) {ComicCard(comic, titleFontSize, titleMaxLine, titleLineHeight, descFontSize, descMaxLine, authorFontSize, authorMaxLine, genreFontSize, genreMaxLine, navController, viewModel)
                    }
                    Button(onClick = {
                        navController.popBackStack()
                    }, modifier = Modifier.fillMaxSize().padding(20.dp), shape = RoundedCornerShape(10.dp)) {
                        Text(text = "Return", modifier = Modifier.padding(end = 10.dp), fontSize = buttonFontSize, textAlign = TextAlign.Center)
                    }
                }
            }
        }

        }
    }

@Composable
fun SettingsScreen(navController: NavHostController) {
    val currentLocales = AppCompatDelegate.getApplicationLocales()
    val currentLang = currentLocales[0]?.language ?: "en"

    val newLang = if (currentLang == "en") "id" else "en"

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {innerPadding ->

        Column(modifier = Modifier.padding(innerPadding).fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
            Text(text = "Change Language?", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 20.dp))

            Button( onClick = {
                AppCompatDelegate.setApplicationLocales(
                    LocaleListCompat.forLanguageTags(newLang)
                )
            }, modifier = Modifier.fillMaxHeight(0.5f).fillMaxWidth(), shape = RoundedCornerShape(10.dp)) {
                Text(text = "Switch Language", modifier = Modifier.padding(end = 10.dp), textAlign = TextAlign.Center)
            }

            Button(onClick = {
                navController.popBackStack()
            }, modifier = Modifier.fillMaxHeight(0.5f).fillMaxWidth(), shape = RoundedCornerShape(10.dp)) {
                Text(text = "Return", modifier = Modifier.padding(end = 10.dp), textAlign = TextAlign.Center)
            }

        }
    }


}
