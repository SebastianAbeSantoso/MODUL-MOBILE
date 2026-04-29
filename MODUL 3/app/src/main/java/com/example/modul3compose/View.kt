package com.example.modul3compose

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController

@Composable
fun RecommendationCarousel(
    items: List<Comic>,
    isLandscape: Boolean,
    navController: NavHostController,
    viewModel: ComicViewModel){
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
                            navController,
                            viewModel
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
    navController: NavHostController,
    viewModel: ComicViewModel
){
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
                Row(modifier = Modifier.fillMaxSize().weight(1f)) { ComicCard(item, titleFontSize, descFontSize, descMaxLine, authorFontSize, genreFontSize, navController, viewModel) }

                Row(modifier = Modifier.background(Color.Black).fillMaxWidth().weight(0.4f).padding(top = buttonPadding), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    Button(onClick = { val intent = Intent(Intent.ACTION_VIEW, item.url.toUri())
                        context.startActivity(intent)
                    }, modifier = Modifier.fillMaxSize().weight(1f).padding(horizontal = 10.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))) {
                        Image(painter = painterResource(R.drawable.mangadex_logo), contentDescription = "mangedex_logo", contentScale = ContentScale.Fit, modifier = Modifier.size(buttonIconSize))
                        Text(text = "Mangadex", modifier = Modifier.padding(start = 10.dp), fontSize = buttonFontSize)
                    }

                    Button(onClick = {
                        navController.navigate("details")
                        viewModel.selectComic(item)
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
    genreFontSize: TextUnit,
    navController: NavHostController,
    viewModel: ComicViewModel
){
    Row(modifier = Modifier
        .fillMaxWidth()) {
        Image(painter = painterResource(item.coverImage),
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .fillMaxHeight()
                .clickable{
                    navController.navigate("details")
                    viewModel.selectComic(item)
                }
                .padding(start = 20.dp)
                .clip(RoundedCornerShape(10)),
            contentScale = ContentScale.Crop,
            contentDescription = "carousel_cover_image")

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable{
                navController.navigate("details")
                viewModel.selectComic(item)
            }
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
