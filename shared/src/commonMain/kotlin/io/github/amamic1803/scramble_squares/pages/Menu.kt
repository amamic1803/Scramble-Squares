package io.github.amamic1803.scramble_squares.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.github.amamic1803.scramble_squares.components.MultiColorOutlinedText

@Composable
@Preview
fun Menu(page: Page = Page.Menu, setPage: (Page) -> Unit = {}) {
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        MultiColorOutlinedText(
            text = "Scramble Squares",
            style = LocalTextStyle.current.copy(fontSize = 64.sp, letterSpacing = 2.sp),
            outlineWidth = 8f,
            fillColor = Color.White,
        )
        Button(
            onClick = { setPage(Page.Game) },
            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
        ) {
            Text("Play")
        }
        Button(
            onClick = { setPage(Page.Settings) },
            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
        ) {
            Text("Settings")
        }
        Button(
            onClick = { setPage(Page.Settings) },
            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
        ) {
            Text("High scores")
        }
    }
}
