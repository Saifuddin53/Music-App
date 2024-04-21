package com.myprojects.musicapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountDialog(dialogOpen: MutableState<Boolean>) {
    if(dialogOpen.value) {
        AlertDialog(
            onDismissRequest = {
                dialogOpen.value = false
            },
            title = {
                Text(text = "Add Account",
                    color = Color.Black)
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { dialogOpen.value = false },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent,
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp
                        )
                    ) {
                        Text(text = "Dismiss",
                            color = Color.Blue)
                    }
                    Button(onClick = { dialogOpen.value = false },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent,
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp
                        )
                    ) {
                        Text(text = "Confirm",
                            color = Color.Blue)
                    }
                }
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    TextField(value = "",
                        onValueChange = {},
                        label = { Text(text = "Enter username", color = Color.Gray) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(value = "",
                        onValueChange = {},
                        label = { Text(text = "Enter password", color = Color.Gray) }
                    )
                }
            }
        )
    }
}


//My designed content for alertdialog using material 3

//OutlinedTextField(value = "",
//onValueChange = {},
//label = {
//    Text(text = "Enter username")
//})
//OutlinedTextField(value = "",
//onValueChange = {},
//label = {
//    Text(text = "Enter password")
//})
//Spacer(modifier = Modifier.height(8.dp))
//Button(onClick = { /*TODO*/ },
//modifier = Modifier.fillMaxWidth()) {
//    Text(text = "Add account")
//}