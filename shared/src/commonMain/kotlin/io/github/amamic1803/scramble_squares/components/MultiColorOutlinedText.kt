package io.github.amamic1803.scramble_squares.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

internal val rainbowOutlines = listOf(
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
    style: TextStyle = LocalTextStyle.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    letterSpacing: TextUnit = 0.sp,             // extra space between letters
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(letterSpacing.value.dp)
    ) {
        text.forEachIndexed { index, char ->
            val outlineColor = outlineColors[index % outlineColors.size]
            OutlinedText(
                text = char.toString(),
                fillColor = fillColor,
                outlineColor = outlineColor,
                outlineWidth = outlineWidth,
                fontSize = fontSize,
                style = style
            )
        }
    }
}
