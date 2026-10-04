package io.github.amamic1803.scramble_squares.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Settings(
    page: Page = Page.Settings,
    setPage: (Page) -> Unit = {},
    toggleTheme: () -> Unit = {})
{
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                onClick = { setPage(Page.Menu) }
            ) {
                Text("back")
            }
        }
        Column {
            Button(
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                onClick = { toggleTheme() }
            ) {
                Text("toggle theme")
            }
        }
    }
}
