package io.github.amamic1803.scramble_squares.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun OutlinedText(
    text: String = "Hello World",
    outlineColor: Color = Color(255, 0, 0),
    modifier: Modifier = Modifier,
    fillColor: Color = Color.White,
    outlineWidth: Float = 3f,
    style: TextStyle = LocalTextStyle.current,    // can specify font size, letter spacing, ...
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
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(wordSpacingDp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center
    ) {
        words.forEach { word ->
            Box {
                // 1. Outline (drawn first)
                Text(
                    text = word,
                    style = style.copy(
                        color = outlineColor,
                        drawStyle = Stroke(
                            width = outlineWidth,
                            join = StrokeJoin.Round   // smoother corners
                        )
                    )
                )

                // 2. Fill (drawn on top)
                Text(
                    text = word,
                    style = style.copy(
                        color = fillColor
                    )
                )
            }
        }
    }
}
