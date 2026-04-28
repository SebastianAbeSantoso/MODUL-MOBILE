package com.example.modul3compose

import android.content.Intent
import android.content.res.Configuration
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul3compose.ui.theme.Modul3ComposeTheme
import androidx.core.net.toUri

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
                MainContent(paddingValues = it, navController)
            }
        }
        composable ("details"){
            DetailsScreen {
                DetailsContent()
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
fun DetailsScreen(
    content: @Composable (PaddingValues) -> Unit
){
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {innerPadding ->
        content(innerPadding)
    }
}

@Composable
fun MainContent(paddingValues: PaddingValues, navController: NavHostController){
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    val comicsCarousel = listOf(
        Comic("Please Bully Me Miss Villainess", R.drawable.cv_cr_1, R.drawable.bg_cr_1, "https://mangadex.org/title/8b34f37a-0181-4f0b-8ce3-01217e9a602c/qing-qifu-wo-ba-eyi-xiaojie", "I, Yvonne, reincarnated into an otome game as the rich villainess. According to the game's plot, a character loathed by everyone such as myself has the main role of bullying the heroine, pushing her towards the various love interests' romantic routes. But it seems like there's something wrong with Elsa, the heroine! She's getting too close to me!", "Romance, Comedy, Transmigration", "Chise, Ciweimao (刺猬猫), 断s, Fairy Club (魔仙社)"),
        Comic("Reverend Insanity", R.drawable.cv_cr_2, R.drawable.bg_cr_2, "https://mangadex.org/title/e1809b04-f7dd-4327-9669-ca83884a1403/reverend-insanity-remake", "A story of a villain, Fang Yuan, who was reborn 500 years into the past with the Spring Autumn Cicada he painstakingly refined. With his profound wisdom, battle and life experiences, he seeks to overcome his foes with skill and wit! Ruthless and amoral, he has no need to hold back as he pursues his ultimate goals.", "Action, Adventure, Wuxia", "Gu Zhen Ren, Rococo (Kodoku Studio), LenHC, Z (Kodoku Studio)"),
        Comic("SSS-Class Suicide Hunter", R.drawable.cv_cr_3, R.drawable.bg_cr_3, "https://mangadex.org/title/4a973243-952e-44d7-a50f-883b4b7c9cc2/sss-geup-jugeoya-saneun-hunter", "In the mysterious, RPG dungeon-like Tower, Gongja Kim lives a mundane existence, envying all the star hunters. One day, his wish for more is granted with a legendary skill to copy others’ abilities… at the cost of his life. Before he can make sense of it, he’s killed by the #1 hunter, the Flame Emperor! But this activates his skill and now he’s copied a new one, the ability to travel back in time upon death.", "Action, Adventure, Time Travel", "Neida (네이다), Shin Noah (신노아), Bill K"),
        Comic("I Got a New Skill Every Time I was Exiled, and After 100 Different Worlds, I was Unmatched", R.drawable.cv_cr_4, R.drawable.bg_cr_4, "https://mangadex.org/title/5f64d517-c4ea-40c0-a463-fcc069d39d3b/tsuihou-sareru-tabi-ni-skill-o-te-ni-ireta-ore-ga-100-no-isekai-de-2-shuume-musou", "An adventurer named Ed was exiled from his party because he was considered useless, but instead of being sad and upset he was actually happy!!? It turns out that it's not without reason that he feels happy when he's exiled from his party, because every time he's been exiled he will receive a skill that makes him even more invincible...", "Reincarnation, Time Travel, Action, Romance", "Hinoura Takumi, Nimori Shimatsukasa"),
        Comic("The Greatest Estate Developer", R.drawable.cv_cr_5, R.drawable.bg_cr_5, "https://mangadex.org/title/d7f56ace-cd30-48b9-8b64-afeca0077fca/yeokdaegeum-yeongji-seolgyesa", "When civil engineering student Suho Kim falls asleep reading a fantasy novel, he wakes up as a character in the book. Suho is now in the body of Lloyd Frontera, a lazy noble who loves to drink, and whose family is in a mountain of debt. Using his engineering knowledge, Suho designs inventions to avert the terrible future that lies in wait for him.", "Comedy, Action, Reincarnation", "Lee Hyunmin (이현민), BK_Moon (문백경), Kim Hyeon-Soo (김현수)"),
        Comic("Lord of the Mysteries", R.drawable.cv_cr_6, R.drawable.bg_cr_6, "https://mangadex.org/title/df3f7834-a65b-4d2c-9388-045c9ec03a35/lord-of-mysteries", "With the rising tide of steam power and machinery, who can come close to being a Beyonder? Shrouded in the fog of history and darkness, who or what is the lurking evil that murmurs into our ears? Waking up to be faced with a string of mysteries, Zhou Mingrui finds himself reincarnated as Klein Moretti in an alternate Victorian era world where he sees a world filled with machinery, cannons, dreadnoughts, airships, difference machines, as well as Potions, Divination, Hexes, Tarot Cards, Sealed Artifacts…", "Transmigration, Historical, Action", "Cuttlefish That Loves Diving (爱潜水的乌贼), sevenballoon"),
        Comic("The Summer You Were There", R.drawable.cv_cr_7, R.drawable.bg_cr_7, "https://mangadex.org/title/6ecc62e4-25ad-4102-b0d8-580a8023d2fb/kimi-to-tsuzuru-utakata", "Shizuku is a shy high schooler who hardly talks to other people. Instead, she loses herself in writing, crafting a novel that she never intends to show anyone. But when her cute, popular classmate Kaori gets her hands on Shizuku’s manuscript, everything changes", "Psychological, Tragedy, Romance", "Yuama"),
        )

    val comicsList = listOf(
        Comic("The Moon on a Rainy Night", R.drawable.cv_cr_1, R.drawable.bg_cr_1, "https://mangadex.org/title/8b34f37a-0181-4f0b-8ce3-01217e9a602c/qing-qifu-wo-ba-eyi-xiaojie", "Yvonne Smollett is reincarnated into an otome game—not as the heroine, but as the villainess. In the original story, her role is to bully the heroine and push her toward various romance routes… before eventually meeting a bad end herself.", "Romance, Comedy, Fantasy, Isekai", "Yuin"),
        Comic("Release That Witch", R.drawable.cv_cr_2, R.drawable.bg_cr_2, "https://genshin-impact.fandom.com/wiki/Eula", "lorem", "ipsum", "dolor"),
        Comic("Legend of The Northern Blade", R.drawable.cv_cr_3, R.drawable.bg_cr_3, "https://genshin-impact.fandom.com/wiki/Eula", "lorem", "ipsum", "dolor"),
        Comic("Return of the Mount Hua Sect", R.drawable.cv_cr_4, R.drawable.bg_cr_4, "https://genshin-impact.fandom.com/wiki/Eula", "lorem", "ipsum", "dolor"),
        Comic("The One Within the Villainess", R.drawable.cv_cr_5, R.drawable.bg_cr_5, "https://genshin-impact.fandom.com/wiki/Eula", "lorem", "ipsum", "dolor"),
        Comic("The King's Avatar", R.drawable.cv_cr_6, R.drawable.bg_cr_6, "https://genshin-impact.fandom.com/wiki/Eula", "lorem", "ipsum", "dolor"),
        Comic("I Made Friends with the Second Prettiest Girl in My Class", R.drawable.cv_cr_7, R.drawable.bg_cr_7, "https://genshin-impact.fandom.com/wiki/Eula", "lorem", "ipsum", "dolor"),
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            RecommendationCarousel(comicsCarousel, paddingValues, isLandscape)
        }

        item {Text(text = "Top Comic",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 25.sp,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
        )
        }

        items(comicsList) {item ->
            ComicList(item, isLandscape, navController)
        }
    }
}

@Composable
fun DetailsContent(){

}

@Composable
fun RecommendationCarousel(items: List<Comic>, paddingValues: PaddingValues, isLandscape: Boolean){
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    val descFontSize =  if (isLandscape) 24.sp else 15.sp
    val descMaxLine = if (isLandscape) 3 else 5

    val titleFontSize = if (isLandscape) 32.sp else 20.sp
    val authorFontSize = if (isLandscape) 24.sp else 20.sp
    val genreFontSize = if (isLandscape) 20.sp else 15.sp

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

                        ComicCard(
                            item,
                            titleFontSize,
                            descFontSize,
                            descMaxLine,
                            authorFontSize,
                            genreFontSize,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ComicList(item: Comic, isLandscape: Boolean, navController: NavHostController){
    val descFontSize =  if (isLandscape) 24.sp else 15.sp
    val descMaxLine = if (isLandscape) 3 else 3

    val titleFontSize = if (isLandscape) 32.sp else 20.sp
    val authorFontSize = if (isLandscape) 24.sp else 20.sp
    val genreFontSize = if (isLandscape) 20.sp else 15.sp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 10.dp, bottom = 20.dp)
    ) {
        val context = LocalContext.current

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
                Row(modifier = Modifier.fillMaxSize().weight(1f)) { ComicCard(item, titleFontSize, descFontSize, descMaxLine, authorFontSize, genreFontSize) }

                Row(modifier = Modifier.background(Color.Black).fillMaxWidth().weight(0.3f).padding(top = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = { val intent = Intent(Intent.ACTION_VIEW, item.url.toUri())
                        context.startActivity(intent)
                    }, modifier = Modifier.fillMaxHeight().weight(1f)) {
                        Image(painter = painterResource(R.drawable.mangadex_logo), contentDescription = "mangedex_logo", contentScale = ContentScale.Fit)
                        Text(text = "Mangadex", modifier = Modifier.padding(start = 10.dp))
                    }
                    Button(onClick = { navController.navigate("details")
                    }, modifier = Modifier.fillMaxHeight().weight(1f)) {
                        Icon(Icons.Default.Menu, contentDescription = "detail_icon")
                        Text(text = "Detail", modifier = Modifier.padding(start = 5.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ComicCard(
    item: Comic,
    titleFontSize: TextUnit,
    descFontSize: TextUnit,
    descMaxLine: Int,
    authorFontSize: TextUnit,
    genreFontSize: TextUnit
){
    Row(modifier = Modifier
        .fillMaxWidth()) {
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
                fontSize = titleFontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = item.description,
                color = Color.White,
                fontSize = descFontSize,
                maxLines = descMaxLine,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 1.5.em,
                textAlign = TextAlign.Justify
            )

            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                Text(text = item.author,
                    color = Color.White,
                    fontSize = authorFontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = item.genre,
                        color = Color.White,
                        fontSize = genreFontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
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