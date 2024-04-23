package com.myprojects.musicapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.myprojects.musicapp.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView() {
    val categories = listOf("Hits", "Happy", "Workout", "Running", "TGIF", "Yoga")
    val grouped = listOf<String>("New Release", "Favourite", "Top rated").groupBy { it[0] }

    LazyColumn {
        grouped.forEach {
            stickyHeader {
                Text(text = it.value[0], modifier = Modifier.padding(16.dp), color = Color.Black)
                LazyRow {
                    items(categories) {
                        category ->
                            BrowserItem(category)
                    }
                }
            }
        }
    }
}

@Composable
fun BrowserItem(name: String, drawable: Int = R.drawable.baseline_apps_24) {
    Column(
        modifier = Modifier
            .padding(6.dp)
            .border(2.dp, Color.Black)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(60.dp)
        ) {
            Text(text = name)
            Icon(painter = painterResource(id = drawable), contentDescription = null)
        }
    }
}