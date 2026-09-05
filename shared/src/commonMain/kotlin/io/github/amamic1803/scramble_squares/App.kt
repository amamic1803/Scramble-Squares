package io.github.amamic1803.scramble_squares

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.russhwolf.settings.ExperimentalSettingsApi
import io.github.amamic1803.scramble_squares.components.Background
import io.github.amamic1803.scramble_squares.pages.Game
import io.github.amamic1803.scramble_squares.pages.Menu
import io.github.amamic1803.scramble_squares.pages.Page
import io.github.amamic1803.scramble_squares.pages.Settings
import io.github.amamic1803.scramble_squares.theme.appTypography
import io.github.amamic1803.scramble_squares.theme.darkScheme
import io.github.amamic1803.scramble_squares.theme.lightScheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalSettingsApi::class)
@Composable
@Preview
fun App() {
    val storage by Storage

    val isSystemDark = isSystemInDarkTheme()
    val isDarkFlow = remember(isSystemDark) {
        storage.getBooleanFlow("darkTheme", isSystemDark)
    }
    val isDark by isDarkFlow.collectAsStateWithLifecycle(isSystemDark)
    val scope = rememberCoroutineScope()
    val toggleTheme: () -> Unit = remember(isDark) { {
        scope.launch {
            storage.putBoolean("darkTheme", !isDark)
        }
    } }

    var page by remember { mutableStateOf(Page.Menu) }
    val setPage = remember { { newPage: Page -> page = newPage } }

    MaterialTheme(
        colorScheme = if (isDark) darkScheme else lightScheme,
        typography = appTypography()
    ) {
        Background(isDark) {
            when (page) {
                Page.Menu -> Menu(page, setPage)
                Page.Game -> Game(page, setPage)
                Page.Settings -> Settings(page, setPage, toggleTheme)
            }
        }
    }
}
