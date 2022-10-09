package com.example.brawlinfo.presentation.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.brawlinfo.R
import com.example.brawlinfo.domain.model.Brawler
import com.example.brawlinfo.presentation.navigation.Screen
import com.example.brawlinfo.presentation.viewmodel.BrawlViewModel
import com.example.brawlinfo.ui.theme.AppFontFamily
import com.example.brawlinfo.ui.theme.Primary
import com.example.brawlinfo.ui.theme.Shapes
import android.graphics.Color as MyColor

@Composable
fun BrawlScreen(
    mainViewModel: BrawlViewModel,
    navHostController: NavHostController
) {

    val brawlers = mainViewModel.brawlersList.value

    if (brawlers == null) {
        mainViewModel.getBrawlers()
        LoadingProgressBar()
    } else  {
        BrawlersList(
            data = brawlers,
            navHostController = navHostController,
            mainViewModel = mainViewModel
        )
        mainViewModel.onBackPressed()
    }
}

@Composable
fun BrawlersList(
    data: List<Brawler>?,
    mainViewModel: BrawlViewModel,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .background(
                color = if (isSystemInDarkTheme()) {
                    Color.Black
                } else Color.DarkGray
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = stringResource(R.string.all_brawlers),
            fontSize = 28.sp,
            fontFamily = AppFontFamily,
            color = Primary
        )
        LazyVerticalGrid(
            modifier = Modifier
                .background(
                    color = if (isSystemInDarkTheme()) {
                        Color.Black
                    } else Color.DarkGray
                )
                .fillMaxSize(),
            content = {
                data?.size?.let {
                    items(it) { item ->
                        BrawlerCard(
                            data[item],
                            mainViewModel,
                            navHostController
                        )
                    }
                }
            },
            verticalArrangement = Arrangement.spacedBy(8.dp),
            columns = GridCells.Adaptive(120.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun BrawlerCard(
    brawler: Brawler,
    viewModel: BrawlViewModel,
    navHostController: NavHostController,
) {
    val color = Color(MyColor.parseColor(brawler.rarity?.color))
    Card(
        modifier = Modifier
            .fillMaxHeight(.33f)
            .border(
                width = 1.dp,
                color = color,
                shape = Shapes.medium
            )
            .clickable {
                viewModel.getBrawlerById(brawler.id)
                navHostController.navigate(
                    route = Screen.BrawlerInfoScreen.route
                )
            },
        shape = Shapes.large,
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = if (isSystemInDarkTheme()) {
                        Color.Black
                    } else Color.DarkGray
                ),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp),
                painter = rememberImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = brawler.imageUrl)
                        .allowHardware(false)
                        .build()
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = brawler.name.toString(),
                    fontSize = 20.sp,
                    fontFamily = AppFontFamily,
                    color = color
                )
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 4.dp,
                        bottom = 16.dp),
                    text = brawler.rarity?.name.toString(),
                    fontSize = 14.sp,
                    fontFamily = AppFontFamily,
                    color = color
                )
            }
        }
    }
}
