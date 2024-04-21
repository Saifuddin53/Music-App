package com.myprojects.musicapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SubscriptionView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Manage Subscription")
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            elevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(
                        modifier = Modifier.padding(bottom = 30.dp)
                    ) {
                        Text(text = "Musical")
                        Text(text = "Free Tier")
                    }
                    Row(
                        modifier = Modifier.padding(top = 40.dp)
                    ) {
                        Text(text = "See all plans", color = Color.Blue)
                        Icon(imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.Blue)
                    }
                }
                Divider()
                Row(
                    modifier = Modifier.padding(vertical = 25.dp, horizontal = 6.dp)
                ) {
                    Icon(imageVector = Icons.Default.AccountCircle,
                        contentDescription = null)
                    Text(text = "Get a plan")
                }
            }
        }
    }
}