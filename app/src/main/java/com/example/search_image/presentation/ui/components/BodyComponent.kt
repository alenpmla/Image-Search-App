package com.example.search_image.presentation.ui.components

import android.content.res.Configuration
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.search_image.presentation.SearchEvent
import com.example.search_image.presentation.viewmodel.HomeScreenViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BodyComponent(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val state = viewModel.state
    val openDialog = remember { mutableStateOf(false) }
    val index = remember { mutableStateOf(0) }
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(
                    SearchEvent.OnSearchQueryChange(it)
                )
            },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(if (isPortrait) 2 else 3),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
        ) {
            items(state.searchLists.size) { i ->
                val search = state.searchLists[i]
                Box(modifier = Modifier.clickable {
                    Log.w("detail_screen", "detail_screen/$i")
                    openDialog.value = true
                    index.value = i

                }) {
                    ListItem(
                        search.user ?: "", search.tags ?: "",
                        search.previewURL ?: ""
                    )
                }
            }

        }

    }
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Show More Details")
            },
            text = {
                Text(
                    "Do you want to see more details about this image?"
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text("Dismiss")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            openDialog.value = false
                            navController.navigate(route = "detail_screen/${index.value}")
                        }
                    ) {
                        Text("Yes")
                    }
                }

            }
        )
    }

}