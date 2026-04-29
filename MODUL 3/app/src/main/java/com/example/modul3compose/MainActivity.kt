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
import androidx.compose.foundation.layout.size
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
            MainScreen(navController)
        }
        composable ("details"){
            DetailsScreen ()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
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
        val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

        val comicsCarousel = listOf(
            Comic(
                "Please Bully Me Miss Villainess",
                R.drawable.cv_cr_1,
                R.drawable.bg_cr_1,
                "https://mangadex.org/title/8b34f37a-0181-4f0b-8ce3-01217e9a602c/qing-qifu-wo-ba-eyi-xiaojie",
                "I, Yvonne, reincarnated into an otome game as the rich villainess. According to the game's plot, a character loathed by everyone such as myself has the main role of bullying the heroine, pushing her towards the various love interests' romantic routes. But it seems like there's something wrong with Elsa, the heroine! She's getting too close to me!",
                "Romance, Comedy, Transmigration",
                "Chise, Ciweimao (刺猬猫), 断s, Fairy Club (魔仙社)"),
            Comic(
                "Reverend Insanity",
                R.drawable.cv_cr_2,
                R.drawable.bg_cr_2,
                "https://mangadex.org/title/e1809b04-f7dd-4327-9669-ca83884a1403/reverend-insanity-remake",
                "A story of a villain, Fang Yuan, who was reborn 500 years into the past with the Spring Autumn Cicada he painstakingly refined. With his profound wisdom, battle and life experiences, he seeks to overcome his foes with skill and wit! Ruthless and amoral, he has no need to hold back as he pursues his ultimate goals.",
                "Action, Adventure, Wuxia",
                "Gu Zhen Ren, Rococo (Kodoku Studio), LenHC, Z (Kodoku Studio)"),
            Comic(
                "SSS-Class Suicide Hunter",
                R.drawable.cv_cr_3,
                R.drawable.bg_cr_3,
                "https://mangadex.org/title/4a973243-952e-44d7-a50f-883b4b7c9cc2/sss-geup-jugeoya-saneun-hunter",
                "In the mysterious, RPG dungeon-like Tower, Gongja Kim lives a mundane existence, envying all the star hunters. One day, his wish for more is granted with a legendary skill to copy others’ abilities… at the cost of his life. Before he can make sense of it, he’s killed by the #1 hunter, the Flame Emperor! But this activates his skill and now he’s copied a new one, the ability to travel back in time upon death.",
                "Action, Adventure, Time Travel",
                "Neida (네이다), Shin Noah (신노아), Bill K"),
            Comic(
                "I Got a New Skill Every Time I was Exiled, and After 100 Different Worlds, I was Unmatched",
                R.drawable.cv_cr_4,
                R.drawable.bg_cr_4,
                "https://mangadex.org/title/5f64d517-c4ea-40c0-a463-fcc069d39d3b/tsuihou-sareru-tabi-ni-skill-o-te-ni-ireta-ore-ga-100-no-isekai-de-2-shuume-musou",
                "An adventurer named Ed was exiled from his party because he was considered useless, but instead of being sad and upset he was actually happy!!? It turns out that it's not without reason that he feels happy when he's exiled from his party, because every time he's been exiled he will receive a skill that makes him even more invincible...",
                "Reincarnation, Time Travel, Action, Romance",
                "Hinoura Takumi, Nimori Shimatsukasa"),
            Comic(
                "The Greatest Estate Developer",
                R.drawable.cv_cr_5,
                R.drawable.bg_cr_5,
                "https://mangadex.org/title/d7f56ace-cd30-48b9-8b64-afeca0077fca/yeokdaegeum-yeongji-seolgyesa",
                "When civil engineering student Suho Kim falls asleep reading a fantasy novel, he wakes up as a character in the book. Suho is now in the body of Lloyd Frontera, a lazy noble who loves to drink, and whose family is in a mountain of debt. Using his engineering knowledge, Suho designs inventions to avert the terrible future that lies in wait for him.",
                "Comedy, Action, Reincarnation",
                "Lee Hyunmin (이현민), BK_Moon (문백경), Kim Hyeon-Soo (김현수)"),
            Comic(
                "Lord of the Mysteries",
                R.drawable.cv_cr_6,
                R.drawable.bg_cr_6,
                "https://mangadex.org/title/df3f7834-a65b-4d2c-9388-045c9ec03a35/lord-of-mysteries",
                "With the rising tide of steam power and machinery, who can come close to being a Beyonder? Shrouded in the fog of history and darkness, who or what is the lurking evil that murmurs into our ears? Waking up to be faced with a string of mysteries, Zhou Mingrui finds himself reincarnated as Klein Moretti in an alternate Victorian era world where he sees a world filled with machinery, cannons, dreadnoughts, airships, difference machines, as well as Potions, Divination, Hexes, Tarot Cards, Sealed Artifacts…",
                "Transmigration, Historical, Action",
                "Cuttlefish That Loves Diving (爱潜水的乌贼), sevenballoon"),
            Comic(
                "The Summer You Were There",
                R.drawable.cv_cr_7,
                R.drawable.bg_cr_7,
                "https://mangadex.org/title/6ecc62e4-25ad-4102-b0d8-580a8023d2fb/kimi-to-tsuzuru-utakata",
                "Shizuku is a shy high schooler who hardly talks to other people. Instead, she loses herself in writing, crafting a novel that she never intends to show anyone. But when her cute, popular classmate Kaori gets her hands on Shizuku’s manuscript, everything changes",
                "Psychological, Tragedy, Romance",
                "Yuama"),
        )

        val comicsList = listOf(
            Comic(
                "The Moon on a Rainy Night",
                R.drawable.cv_li_1,
                R.drawable.bg_li_1,
                "https://mangadex.org/title/c8a0426d-539b-4f6a-9537-fbccf4962529/amayo-no-tsuki",
                "One rainy night, Saki is rushing to a piano lesson when she crashes into a beautiful, long-haired girl, dropping her sheet music in the process. Saki stutters an apology, but the girl simply hands back her sheet music and leaves without a word. Saki begins her first day of high school the following morning, only to find the stranger from the night before sitting at the desk next to hers. She learns that the girl's name is Kanon and that she is not quite completely deaf, but very hard of hearing. Though Kanon needs to be close to people to read their lips, she tends to push people away with her icy demeanor. Through one kind gesture, Saki slowly begins breaking down the walls around Kanon, even as she feels something new blossoming within her.",
                "",
                ""),
            Comic(
                "Movies Are Real",
                R.drawable.cv_li_2,
                R.drawable.bg_li_2,
                "https://mangadex.org/title/ff2f35ff-2641-4a1b-8477-df933efd4eba/movies-are-real",
                "While working as a small supporting actor in a movie, Ki Man Sung has nothing else going for him. However, that was when ,through mysterious circumstance, the movie set had become reality during filming. With the sound of the director’s cut, during that short take, he’s not an actor but actually thrown into the events occurring, showing the pain and emotions he faces in that moment. He can only come back if he creates that perfect take…!",
                "",
                ""),
            Comic(
                "Legend of The Northern Blade",
                R.drawable.cv_li_3,
                R.drawable.bg_li_3,
                "https://mangadex.org/title/9ed16bc9-f570-4e71-8dda-aebc098b683b/the-legend-of-the-northern-blade",
                "When the world was plunged into darkness by the ‘Silent Night’, Martial Artists from all over the place gathered to form the ‘Northern Heavenly Sect’. With overwhelming strength from the Northern Heavenly Sect, the Silent Night was pushed away and the people began to enjoy peace once more. However, as time passed the martial artists from the mainlands began to conspire against the ‘Northern Heavenly Sect’, and eventually caused the death of the Fourth Generation Sect Leader, Jin Kwan-Ho, destroying the sect with it. As everyone left the sect, Jin Kwan-Ho’s only son, Jin Mu-Won was left behind. Mu-Won has never learned anything about martial arts, but he eventually finds the Martial Techniques secretly left behind by his father and begins to acquire the martial arts of the Northern Heavenly Sect. Then one day, a mysterious girl appears before Mu-Won…!!",
                "",
                ""),
            Comic(
                "Return of the Mount Hua Sect",
                R.drawable.cv_li_4,
                R.drawable.bg_li_4,
                "https://mangadex.org/title/f0f62b75-5989-4f32-9b59-ab56abe35fc1/return-of-the-blossoming-blade",
                "Chung Myung, The 13th Disciple of the Mount Hua Sect, One of the 3 Great Swordsmen, Plum Blossom Sword Saint, defeated Chun Ma, who has brought destruction and disarray onto the world. After the battle, he breathes his last breath on top of the headquarter mountain of the Heavenly Demon Sect. He is reborn after 100 years in the body of a child. ……What? The Mount Hua Sect has fallen? What kind of nonsense is that!?",
                "",
                ""),
            Comic(
                "The One Within the Villainess",
                R.drawable.cv_li_5,
                R.drawable.bg_li_5,
                "https://mangadex.org/title/6df29d13-2dab-4ca6-a0b5-74070abf3e1d/akuyaku-reijou-no-naka-no-hito",
                "In order to clear the name of 'Emi', a girl who had reincarnated as Remilia, the villainess of an otome game, the real Remilia who had been watching all along inside awakens.",
                "",
                ""),
            Comic(
                "The King's Avatar",
                R.drawable.cv_li_6,
                R.drawable.bg_li_6,
                "https://mangadex.org/title/1930d635-b170-417f-b8a8-f84b881bcc7d/quanzhi-gaoshou",
                "No one could ever come close to Ye Qiu’s skills in Glory, a popular MMO that took the world by storm. However, his professional team replaced him as captain and seized his account. Ye Qiu was left to start anew at an internet café as a network manager. When the new 10th server launched, he dove back in, armed with a decade of experience and an unfinished custom weapon. His path to redemption, filled with strategy and memories, marks his thrilling return to the top.",
                "",
                ""),
            Comic(
                "I Made Friends with the Second Prettiest Girl in My Class",
                R.drawable.cv_li_7,
                R.drawable.bg_li_7,
                "https://mangadex.org/title/8ef11280-30bc-434b-bf61-c61e092905ac/class-de-2-banme-ni-kawaii-onnanoko-to-tomodachi-ni-natta",
                "I, Maki Maehara, am a class loner. My first friend ever is Umi Asanagi. She is always at the center of the social circle, the girl the guys secretly whisper is the \"second cutest in the class.\" I thought a background character like me lived in a completely different world from her, but it turns out she’s a huge fan of B-movies!? After becoming friends through a twist of fate, Asanagi secretly comes to hang out at my house every Friday after school. Movies, games, manga, we have a great time together sharing the exact same hobbies. \"Hey, Maehara, look! I'll let you sit right here as a special treat.\" \"That's my bed to begin with...\" \"Just for today, it's my bed. Come on, get over here.\" Aren't you a little too close, Asanagi?",
                "",
                ""),
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                RecommendationCarousel(comicsCarousel, isLandscape)
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
}

@Composable
fun DetailsScreen(){
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) { }
    }
}

@Composable
fun LanguageScreen(){}

@Composable
fun RecommendationCarousel(
    items: List<Comic>,
    isLandscape: Boolean){
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    val descFontSize =  if (isLandscape) 24.sp else 15.sp
    val descMaxLine = if (isLandscape) 3 else 5

    val titleFontSize = if (isLandscape) 32.sp else 20.sp
    val authorFontSize = if (isLandscape) 24.sp else 20.sp
    val genreFontSize = if (isLandscape) 20.sp else 15.sp

    val cardHeight = if (isLandscape) 400.dp else 300.dp

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        state = listState,
        flingBehavior = flingBehavior
    ) {
        items(items){item ->
            Card(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .height(cardHeight),
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
fun ComicList(
    item: Comic,
    isLandscape: Boolean,
    navController: NavHostController){
    val descFontSize =  if (isLandscape) 24.sp else 15.sp
    val descMaxLine = if (isLandscape) 3 else 3

    val titleFontSize = if (isLandscape) 32.sp else 20.sp
    val authorFontSize = if (isLandscape) 24.sp else 20.sp
    val genreFontSize = if (isLandscape) 20.sp else 15.sp

    val buttonFontSize = if (isLandscape) 30.sp else 20.sp
    val buttonPadding = if (isLandscape) 20.dp else 10.dp
    val buttonIconSize = if (isLandscape) 32.dp else 24.dp
    val cardHeight = if (isLandscape) 280.dp else 200.dp


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
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

                Row(modifier = Modifier.background(Color.Black).fillMaxWidth().weight(0.4f).padding(top = buttonPadding), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    Button(onClick = { val intent = Intent(Intent.ACTION_VIEW, item.url.toUri())
                        context.startActivity(intent)
                    }, modifier = Modifier.fillMaxSize().weight(1f).padding(horizontal = 10.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))) {
                        Image(painter = painterResource(R.drawable.mangadex_logo), contentDescription = "mangedex_logo", contentScale = ContentScale.Fit, modifier = Modifier.size(buttonIconSize))
                        Text(text = "Mangadex", modifier = Modifier.padding(start = 10.dp), fontSize = buttonFontSize)
                    }

                    Button(onClick = { navController.navigate("details")
                    }, modifier = Modifier.fillMaxSize().weight(1f).padding(horizontal = 10.dp)) {
                        Icon(Icons.Default.Menu, contentDescription = "detail_icon", modifier = Modifier.size(buttonIconSize))
                        Text(text = "Detail", modifier = Modifier.padding(start = 10.dp), fontSize = buttonFontSize)
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