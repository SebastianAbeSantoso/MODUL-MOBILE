package com.example.modul3compose

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.stringResource
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
    val descMaxLine = if (isLandscape) 2 else 6

    val titleFontSize = if (isLandscape) 32.sp else 20.sp
    val titleMaxLine = 1
    val titleLineHeight = 10.sp

    val authorFontSize = if (isLandscape) 24.sp else 20.sp
    val authorMaxLine = 1

    val genreFontSize = if (isLandscape) 20.sp else 15.sp
    val genreMaxLine = 1



    val cardHeight = if (isLandscape) 280.dp else 300.dp

    LazyRow(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        state = listState,
        flingBehavior = flingBehavior
    ) {
        items(items){item ->
            Card(modifier = Modifier
                    .fillParentMaxWidth()
                    .height(cardHeight),
                RoundedCornerShape(0.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()){
                    Image(painter = painterResource(item.backgroundImage),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        contentDescription = stringResource(R.string.cd_carousel_bg_image))

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
                        Text(text = stringResource(R.string.label_recommendation),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp))

                        ComicCard(item,
                            titleFontSize,
                            titleMaxLine,
                            titleLineHeight,
                            descFontSize,
                            descMaxLine,
                            authorFontSize,
                            authorMaxLine,
                            genreFontSize,
                            genreMaxLine,
                            navController,
                            viewModel,
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
    val descMaxLine = if (isLandscape) 2 else 3

    val titleFontSize = if (isLandscape) 32.sp else 20.sp
    val titleMaxLine = 1
    val titleLineHeight = 10.sp

    val authorFontSize = if (isLandscape) 24.sp else 20.sp
    val authorMaxLine = 1

    val genreFontSize = if (isLandscape) 20.sp else 15.sp
    val genreMaxLine = 1

    val buttonFontSize = if (isLandscape) 30.sp else 18.sp
    val buttonPadding = if (isLandscape) 20.dp else 15.dp
    val buttonIconSize = if (isLandscape) 32.dp else 24.dp
    val cardHeight = if (isLandscape) 280.dp else 300.dp

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(cardHeight)
        .padding(top = 10.dp, bottom = 20.dp)
        .border(2.dp, Color.DarkGray, shape = RoundedCornerShape(10.dp))

    ) {
        val context = LocalContext.current

        Box(modifier = Modifier.fillMaxSize()){
            Image(painter = painterResource(item.backgroundImage),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.cd_carousel_bg_image))

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
                Row(modifier = Modifier.fillMaxSize().weight(1f)
                ){
                    ComicCard(item,
                    titleFontSize,
                    titleMaxLine,
                    titleLineHeight,
                    descFontSize,
                    descMaxLine,
                    authorFontSize,
                    authorMaxLine,
                    genreFontSize,
                    genreMaxLine,
                    navController,
                    viewModel,
                    ) }

                Row(modifier = Modifier.background(Color.Black)
                    .fillMaxWidth().weight(0.5f)
                    .padding(vertical = buttonPadding, horizontal = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = { val intent = Intent(Intent.ACTION_VIEW,
                        item.url.toUri())
                        context.startActivity(intent)
                    }, modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight(0.85f)
                        .weight(1f)
                        .padding(horizontal = 6.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))) {
                        Image(painter = painterResource(R.drawable.mangadex_logo),
                            contentDescription = stringResource(R.string.cd_mangadex_logo),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(buttonIconSize))
                        Text(text = stringResource(R.string.btn_mangadex),
                            modifier = Modifier.padding(start = 10.dp),
                            color = Color.Black,
                            fontSize = buttonFontSize)
                    }

                    Button(onClick = { navController.navigate("details")
                        viewModel.selectComic(item)
                    }, modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight(0.85f)
                        .weight(1f)
                        .padding(horizontal = 6.dp),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Icon(Icons.Default.Menu,
                            contentDescription = stringResource(R.string.cd_detail_icon),
                            modifier = Modifier.size(buttonIconSize))
                        Text(text = stringResource(R.string.btn_detail),
                            modifier = Modifier.padding(start = 10.dp),
                            color = Color.Black,
                            fontSize = buttonFontSize)
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
    titleMaxLine: Int,
    titleLineHeight: TextUnit,
    descFontSize: TextUnit,
    descMaxLine: Int,
    authorFontSize: TextUnit,
    authorMaxLine: Int,
    genreFontSize: TextUnit,
    genreMaxLine: Int,
    navController: NavHostController,
    viewModel: ComicViewModel,
    ){

    val genresText = item.genres.map { stringResource(it) }.joinToString(", ")
    val descriptionText = item.description.map { stringResource(it) }.joinToString("\n\n")

    Row(modifier = Modifier.fillMaxWidth()) {
        Image(painter = painterResource(item.coverImage),
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .fillMaxHeight()
                .padding(start = 20.dp)
                .clip(RoundedCornerShape(10))
                .clickable{
                    navController.navigate("details")
                    viewModel.selectComic(item)
                },
            contentScale = ContentScale.Crop,

            contentDescription = stringResource(R.string.cd_cover_image)
        )

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(10.dp)
            .clickable{
                navController.navigate("details")
                viewModel.selectComic(item)
                }
            ) {
            Text(text = item.title,
                color = Color.White,
                fontSize = titleFontSize,
                fontWeight = FontWeight.Bold,
                maxLines = titleMaxLine,
                overflow = TextOverflow.Ellipsis,
                lineHeight = titleLineHeight
            )
            Text(text = descriptionText,
                color = Color.White,
                fontSize = descFontSize,
                maxLines = descMaxLine,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 1.5.em,
                textAlign = TextAlign.Justify
            )

            Column(Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(text = item.author,
                    color = Color.White,
                    fontSize = authorFontSize,
                    maxLines = authorMaxLine,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 5.dp)
                )

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = genresText,
                        color = Color.White,
                        fontSize = genreFontSize,
                        maxLines = genreMaxLine,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}