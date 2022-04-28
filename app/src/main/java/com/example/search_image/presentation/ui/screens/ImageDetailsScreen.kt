package com.example.search_image.presentation.ui.screens

import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.search_image.domain.entities.Search
import com.example.search_image.presentation.viewmodel.HomeScreenViewModel

@Composable
fun ImageDetailsScreen(
    navController: NavHostController,
    index: Int?,
    viewModel: HomeScreenViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val state = viewModel.state
    val search = state.searchLists.elementAt(index ?: 0)
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    Scaffold(modifier = Modifier, topBar = {
        AppBarComponent(navController = navController)
    }) {
        if (isPortrait) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                UserDetailsHeader(search = search)
                LargeImage(search = search)
                Spacer(modifier = Modifier.height(16.dp))
                ImageInfoComponent(search = search)
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    UserDetailsHeader(search = search)
                    ImageInfoComponent(search = search)
                }
                Box(
                    modifier = Modifier.weight(2f)
                ) {
                    LargeImage(search = search)
                }

            }
        }

    }
}

@Composable
fun SingleInfoItem(title: String, value: String?) {
    Column() {
        Row(modifier = Modifier) {
            Text(text = "${title}: ", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
            Text(text = value ?: "", fontFamily = FontFamily.Serif)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Gray.copy(alpha = 0.15f))
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun UserDetailsHeader(search: Search) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(50.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(search.userImageURL)
                    .crossfade(true)
                    .build(),
                contentDescription = "item image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = search.user ?: "")
    }
}

@Composable
fun LargeImage(search: Search) {
    val imageAspectRation = search.imageWidth / search.imageHeight

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(search.largeImageURL)
            .crossfade(true)
            .build(),
        loading = {
            Box(
                modifier = Modifier.size(50.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        contentDescription = "item image",
        contentScale = ContentScale.None,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(imageAspectRation),
    )
}

@Composable
fun ImageInfoComponent(search: Search) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        SingleInfoItem(title = "Uploaded By", value = search.user)
        SingleInfoItem(title = "Tags", value = search.tags)
        SingleInfoItem(title = "Likes", value = search.likes.toString())
        SingleInfoItem(title = "Downloads", value = search.downloads.toString())
        SingleInfoItem(title = "Comments", value = search.comments.toString())
    }
}

@Composable
fun AppBarComponent(navController: NavHostController) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            Icons.Rounded.ArrowBack,
            contentDescription = "print",
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            "Image Details", textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
    }
}