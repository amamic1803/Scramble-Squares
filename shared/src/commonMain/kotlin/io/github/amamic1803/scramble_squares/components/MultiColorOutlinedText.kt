package io.github.amamic1803.scramble_squares.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

private val rainbowOutlines = listOf(
    Color(0xFFE53935), // red
    Color(0xFFFB8C00), // orange
    Color(0xFFFDD835), // yellow
    Color(0xFF43A047), // green
    Color(0xFF1E88E5), // blue
    Color(0xFF8E24AA), // purple
)

@Composable
@Preview
fun MultiColorOutlinedText(
    text: String = "Hello World",
    outlineColors: List<Color> = rainbowOutlines,    // one color per letter (cycles if shorter)
    modifier: Modifier = Modifier,
    fillColor: Color = Color.White,
    outlineWidth: Float = 3f,
    style: TextStyle = LocalTextStyle.current,       // can specify font size, letter spacing, ...
) {
    val density = LocalDensity.current
    val letterSpacingDp = remember(density, style.letterSpacing) {
        with(density) {
            style.letterSpacing.toDp()
        }
    }
    val wordSpacingDp = remember(density, style.fontSize, letterSpacingDp) {
        with(density) {
            style.fontSize.toDp() * 0.35f + letterSpacingDp * 2f
        }
    }

    val words = remember(text) { text.split(Regex("\\s+")) }
    var colorIndex = 0
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(wordSpacingDp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center
    ) {
        words.forEach { word ->
            Row(horizontalArrangement = Arrangement.spacedBy(letterSpacingDp)) {
                word.forEach { char ->
                    val outlineColor = outlineColors[colorIndex % outlineColors.size]
                    OutlinedText(
                        text = char.toString(),
                        fillColor = fillColor,
                        outlineColor = outlineColor,
                        outlineWidth = outlineWidth,
                        style = style
                    )
                    colorIndex++
                }
            }
        }
    }
}
