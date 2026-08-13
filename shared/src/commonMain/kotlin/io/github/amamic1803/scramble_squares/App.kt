package io.github.amamic1803.scramble_squares

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import io.github.amamic1803.scramble_squares.components.Background
import io.github.amamic1803.scramble_squares.pages.Game
import io.github.amamic1803.scramble_squares.pages.Menu
import io.github.amamic1803.scramble_squares.pages.Page
import io.github.amamic1803.scramble_squares.pages.Settings
import io.github.amamic1803.scramble_squares.theme.appTypography
import io.github.amamic1803.scramble_squares.theme.darkScheme
import io.github.amamic1803.scramble_squares.theme.lightScheme

@Composable
@Preview
fun App() {
    val isSystemDark = isSystemInDarkTheme()
    var darkTheme by remember { mutableStateOf(isSystemDark) }
    val toggleTheme = { darkTheme = !darkTheme }

    var page by remember { mutableStateOf(Page.Menu) }
    val setPage = { newPage: Page -> page = newPage }

    MaterialTheme(
        colorScheme = if (darkTheme) darkScheme else lightScheme,
        typography = appTypography()
    ) {
        Background(
            dark = darkTheme
        ) {
            when (page) {
                Page.Menu -> Menu(page, setPage)
                Page.Game -> Game(page, setPage)
                Page.Settings -> Settings(page, setPage, toggleTheme)
            }
        }
    }
}
