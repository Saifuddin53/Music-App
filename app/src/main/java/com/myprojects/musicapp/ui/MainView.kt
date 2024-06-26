package com.myprojects.musicapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
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
import com.myprojects.musicapp.AddAccountDialog
import com.myprojects.musicapp.MainViewModel
import com.myprojects.musicapp.Navigation
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
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

    val isFullScreen by remember {
        mutableStateOf(false)
    }
    val modifier = if(isFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    val title = remember {
        mutableStateOf(currentScreen.title)
    }
    
    val dialogState = remember {
        mutableStateOf(false)
    }

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {it != ModalBottomSheetValue.HalfExpanded}
    )

    val roundedCorner = if(isFullScreen) 0.dp else 12.dp
    
    val bottomBar: @Composable () -> Unit = {
        if(currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home){
            BottomNavigation(modifier = Modifier.wrapContentSize()) {
                screensInBottomBar.forEach {
                    item ->
                    val isSelected = currentRoute == item.bRoute
                    val color = if(isSelected) Color.White else Color.Black
                    BottomNavigationItem(selected = currentRoute == item.bRoute,
                        onClick = {
                            controller.navigate(item.bRoute)
                            title.value = item.bTitle},
                        icon = {
                            Icon(painter = painterResource(id = item.icon),
                            contentDescription = null,
                                tint = color)
                               },
                        label = {
                            Text(text = item.bTitle, color = color)
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )
                }
            }
        }
    }

    ModalBottomSheetLayout(sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCorner, topEnd = roundedCorner),
        sheetContent = {
        MoreBottomSheet(modifier = modifier)
    }) {
        Scaffold(
            bottomBar = bottomBar,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = title.value)
                    },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                if(modalSheetState.isVisible) {
                                    modalSheetState.hide()
                                }else {
                                    modalSheetState.show()
                                }
                            }
                        },
                        ) {
                            Icon(imageVector = Icons.Default.MoreVert,
                                contentDescription = "",
                                tint = Color.White)
                        }
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
                                dialogState.value = true
                            }else {
                                controller.navigate(item.dRoute)
                                viewModel.setCurrentScreen(item)
                                title.value = item.dTitle
                            }
                        }
                    }
                }
            }
        ) {
            Navigation(navController = controller, pd = it)
            AddAccountDialog(dialogOpen = dialogState)
        }
    }
}

@Composable
fun MoreBottomSheet(modifier: Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(300.dp)
        .background(
            MaterialTheme.colors.primarySurface
        )) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
             Row(
                 modifier = Modifier.padding(8.dp)
             ) {
                 Icon(imageVector = Icons.Default.Settings,
                     contentDescription = null,
                     modifier = Modifier.padding(end = 6.dp))
                 Text(text = "Settings")
             }
            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Share,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 6.dp))
                Text(text = "Share")
            }
            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 6.dp))
                Text(text = "Help")
            }
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

