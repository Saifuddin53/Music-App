package com.myprojects.musicapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.myprojects.musicapp.ui.Screen

class MainViewModel: ViewModel() {
    private val _currentScreen: MutableState<Screen> =
        mutableStateOf(Screen.DrawerScreen.Account)

    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }
}