package com.example.brawlinfo.presentation.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import com.example.brawlinfo.domain.model.Brawler
import com.example.brawlinfo.presentation.viewmodel.BrawlViewModel
import com.example.brawlinfo.ui.theme.AppFontFamily
import com.example.brawlinfo.ui.theme.Primary
import com.example.brawlinfo.ui.theme.Shapes
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import android.graphics.Color as MyColor

var color = Primary

@Composable
fun BrawlerInfoScreen(
    mainViewModel: BrawlViewModel
) {
    val brawler = mainViewModel.brawlerById.value
    if (brawler == null) {
        LoadingProgressBar()
    } else {
        color = Color(MyColor.parseColor(brawler.rarity?.color))
        BrawlerInfo(
            brawler = brawler
        )
    }
}

@Composable
fun BrawlerInfo(
    brawler: Brawler?
) {
    Column(
        modifier = Modifier
            .background(
                color = if (isSystemInDarkTheme()) {
                    Color.Black
                } else Color.DarkGray
            )
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = "Brawler's Info",
            fontSize = 28.sp,
            fontFamily = AppFontFamily,
            color = color
        )
        Image(
            painter = rememberImagePainter(brawler?.imageUrl.toString()),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(top = 32.dp),
            contentScale = ContentScale.Fit
        )
        Title(text = brawler?.name.toString())
        OneCharacteristic("Rarity:", brawler?.rarity?.name)
        OneCharacteristic("Class:", brawler?.category?.name)
        OneCharacteristic("Id:", brawler?.avatarId?.toString())
        Text(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    bottom = 12.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            text = brawler?.description.toString(),
            fontSize = 18.sp,
            fontFamily = AppFontFamily,
            color = color
        )
        brawler?.gadgets?.let { gadgets ->
            if (gadgets.isNotEmpty()) {
                Title(text = "Gadgets")
                for(gadget in gadgets) {
                    Perk(
                        imageUrl = gadget.imageUrl.toString(),
                        name = gadget.name.toString(),
                        description = gadget.description.toString()
                    )
                }
            }
        }
        brawler?.starPowers?.let { starPowers ->
            if (starPowers.isNotEmpty()) {
                Title(text = "Star Powers")
                for(starPower in starPowers) {
                    Perk(
                        imageUrl = starPower.imageUrl.toString(),
                        name = starPower.name.toString(),
                        description = starPower.description.toString()
                    )
                }
            }
        }
//        VideoPlayerScreen()
    }
}

@Composable
fun Title(
    text: String
) {
    Text(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 28.dp),
        text = text,
        fontSize = 32.sp,
        fontFamily = AppFontFamily,
        color = color
    )
}

@Composable
fun OneCharacteristic(
    property: String,
    value: String?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 84.dp,
                vertical = 2.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = property,
            fontSize = 20.sp,
            fontFamily = AppFontFamily,
            color = color
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = value.toString(),
            fontSize = 20.sp,
            fontFamily = AppFontFamily,
            color = color
        )
    }
}

@Composable
fun Perk(
    imageUrl: String,
    name: String,
    description: String,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable {},
        shape = Shapes.large,
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier.background(
                color = if (isSystemInDarkTheme()) {
                    Color.Black
                } else Color.DarkGray
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp,
                        vertical = 2.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier.size(84.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = name,
                    fontSize = 20.sp,
                    fontFamily = AppFontFamily,
                    color = color
                )
            }
            Text(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 12.dp
                    ),
                text = description,
                fontSize = 18.sp,
                fontFamily = AppFontFamily,
                color = color
            )
        }
    }
}

@Composable
fun VideoPlayerScreen() {
    val context = LocalContext.current
    var playWhenReady by remember { mutableStateOf(true) }
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            playWhenReady = playWhenReady
            prepare()
            play()
        }
    }
    DisposableEffect(
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false
                }
            },
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}
