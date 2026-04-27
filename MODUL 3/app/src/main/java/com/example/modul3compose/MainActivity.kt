package com.example.modul3compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul3compose.ui.theme.Modul3ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul3ComposeTheme(darkTheme = true) {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController) {
                MainContent(paddingValues = it)
            }
        }
        composable ("setting"){
            SettingScreen{
                SettingContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
){
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
        content(innerPadding)
    }
}

@Composable
fun SettingScreen(
    content: @Composable (PaddingValues) -> Unit
){
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {innerPadding ->
        content(innerPadding)
    }
}

@Composable
fun MainContent(paddingValues: PaddingValues){
    val comicsCarousel = listOf(
        Comic("Please Bully Me Miss Villainess", R.drawable.cover_carousel_1, R.drawable.background_carousel_1, "https://mangadex.org/title/8b34f37a-0181-4f0b-8ce3-01217e9a602c/qing-qifu-wo-ba-eyi-xiaojie", "Yvonne Smollett is reincarnated into an otome game—not as the heroine, but as the villainess. In the original story, her role is to bully the heroine and push her toward various romance routes… before eventually meeting a bad end herself.", "Romance, Comedy, Fantasy, Isekai", "Yuin"),
        Comic("Eu", R.drawable.image, R.drawable.image, "https://genshin-impact.fandom.com/wiki/Eula", "lorem", "ipsum", "dolor"),
        Comic("Eul", R.drawable.image, R.drawable.image, "https://genshin-impact.fandom.com/wiki/Eula", "lorem", "ipsum", "dolor"),
        Comic("Eula", R.drawable.image, R.drawable.image, "https://genshin-impact.fandom.com/wiki/Eula", "lorem", "ipsum", "dolor"),
        Comic("Eular", R.drawable.image, R.drawable.image, "https://genshin-impact.fandom.com/wiki/Eula", "lorem", "ipsum", "dolor"),
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            RecommendationCarousel(comicsCarousel, paddingValues)
        }

        item {Text(text = "Top Comic",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 25.sp,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
        )
        }

        item(5) {}
    }
}

@Composable
fun SettingContent(){

}

@Composable
fun RecommendationCarousel(items: List<Comic>, paddingValues: PaddingValues){
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
        flingBehavior = flingBehavior
    ) {
        items(items){item ->
            Card(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .height(400.dp)
                    .padding(paddingValues),
                RoundedCornerShape(0.dp)
                ) {
                Box(modifier = Modifier.fillMaxSize()){
                    Image(painter = painterResource(item.backgroundImage),
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

                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(text = "My Recommendation",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp))

                        Row(modifier = Modifier.fillMaxSize()) {
                            Image(painter = painterResource(item.coverImage),
                                modifier = Modifier
                                    .fillMaxWidth(0.4f)
                                    .fillMaxHeight()
                                    .padding(start = 20.dp)
                                    .clip(RoundedCornerShape(10)),
                                contentScale = ContentScale.Crop,
                                contentDescription = "carousel_cover_image")

                            Column(modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(10.dp)) {
                                Text(text = item.title,
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(text = item.description,
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    maxLines = 4,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                                    Text(text = item.author,
                                    color = Color.White,
                                    fontSize = 20.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.padding(vertical = 10.dp)
                                )
                                    Row(modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(text = item.genre,
                                            color = Color.White,
                                            fontSize = 15.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

data class Comic (
    val title: String,
    val coverImage: Int,
    val backgroundImage: Int,
    val url: String,
    val description: String,
    val genre: String,
    val author: String
)