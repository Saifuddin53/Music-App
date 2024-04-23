package com.myprojects.musicapp.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BrowseView() {
    val categories = listOf("Hits", "Happy", "Workout", "Running", "TGIF", "Yoga")

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(categories) {
                category ->
            BrowserItem(category, modifier = Modifier.fillMaxWidth())
        }
    }
}