package com.example.search_image.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.search_image.presentation.ui.components.BodyComponent


@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
            ) {
                Text(
                    "Search Images", textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        isFloatingActionButtonDocked = true,
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.White)
        ) {
            BodyComponent(navController)
        }
    }
}