package io.github.amamic1803.scramble_squares

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Scramble-Squares",
    ) {
        App()
    }
}