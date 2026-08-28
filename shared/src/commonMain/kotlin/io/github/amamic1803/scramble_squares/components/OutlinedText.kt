package io.github.amamic1803.scramble_squares.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit

@Composable
@Preview
fun OutlinedText(
    text: String = "Hello World",
    modifier: Modifier = Modifier,
    fillColor: Color = Color.White,
    outlineColor: Color = Color(0xFF2196F3),
    outlineWidth: Float = 3f,                  // thickness in px
    style: TextStyle = LocalTextStyle.current,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    val mergedStyle = style.merge(
        TextStyle(fontSize = fontSize)
    )

    Box(modifier = modifier) {
        // 1. Outline (drawn first)
        Text(
            text = text,
            style = mergedStyle.copy(
                color = outlineColor,
                drawStyle = Stroke(
                    width = outlineWidth,
                    join = StrokeJoin.Round   // smoother corners
                )
            )
        )

        // 2. Fill (drawn on top)
        Text(
            text = text,
            style = mergedStyle.copy(
                color = fillColor
            )
        )
    }
}
