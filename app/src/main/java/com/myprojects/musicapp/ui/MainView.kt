package com.myprojects.musicapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.myprojects.musicapp.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainView() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()

    val controller = rememberNavController()
    val navStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navStackEntry?.destination?.route

    val currentScreen = viewModel.currentScreen.value

    val title = remember {
        mutableStateOf(currentScreen.title)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.AccountCircle,
                            contentDescription = "")
                    }
                }
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            LazyColumn(
                modifier = Modifier.padding(top = 24.dp)
            ) {
                items(screenInDrawer) {
                    item ->
                    DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        if(item.dRoute == "add_account") {
                            //open dialog
                        }else {
                            controller.navigate(item.dRoute)
                            title.value = item.dTitle
                        }
                    }
                }
            }
        }
    ) {
        Row {

        }
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if(selected) Color.Gray else Color.White
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onDrawerItemClicked() }
            .background(background),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = item.dTitle,
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerItemPreview() {
    DrawerItem(selected = false, item = Screen.DrawerScreen.Account) {

    }
}