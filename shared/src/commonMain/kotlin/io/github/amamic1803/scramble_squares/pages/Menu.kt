package io.github.amamic1803.scramble_squares.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.github.amamic1803.scramble_squares.Greeting
import io.github.amamic1803.scramble_squares.components.MultiColorOutlinedText
import io.github.amamic1803.scramble_squares.components.OutlinedText
import org.jetbrains.compose.resources.painterResource
import scramble_squares.shared.generated.resources.Res
import scramble_squares.shared.generated.resources.compose_multiplatform

@Composable
@Preview
fun Menu(page: Page = Page.Menu, setPage: (Page) -> Unit = {}) {
    var showContent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val rainbowOutlines = listOf(
            Color(0xFFE53935), // red
            Color(0xFFFB8C00), // orange
            Color(0xFFFDD835), // yellow
            Color(0xFF43A047), // green
            Color(0xFF1E88E5), // blue
            Color(0xFF8E24AA), // purple
        )

        MultiColorOutlinedText(
            text = "SCRAMBLE",
            outlineColors = rainbowOutlines,
            fontSize = 64.sp,
            outlineWidth = 8f,
            fillColor = Color.White
        )
        OutlinedText(
            text = "Scramble Squares",
            fontSize = 64.sp,
            outlineWidth = 5f,          // adjust for thinness
            fillColor = Color.White,
            outlineColor = Color(0xFF1565C0)  // or whatever blue you like
        )
        Button(onClick = { setPage(Page.Settings) }) {
            Text("settings")
        }
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}
