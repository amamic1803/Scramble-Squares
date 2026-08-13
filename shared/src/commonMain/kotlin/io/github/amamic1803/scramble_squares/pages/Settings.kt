package io.github.amamic1803.scramble_squares.pages

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Settings(
    page: Page = Page.Settings,
    setPage: (Page) -> Unit = {},
    toggleTheme: () -> Unit = {})
{
    Button(onClick = { toggleTheme() }) {
        Text("toggle theme")
    }
}