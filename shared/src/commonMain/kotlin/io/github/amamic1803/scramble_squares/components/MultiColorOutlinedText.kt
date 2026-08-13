package io.github.amamic1803.scramble_squares.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MultiColorOutlinedText(
    text: String,
    outlineColors: List<Color>,                 // one color per letter (cycles if shorter)
    modifier: Modifier = Modifier,
    fillColor: Color = Color.White,
    outlineWidth: Float = 3f,
    style: TextStyle = LocalTextStyle.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    letterSpacing: TextUnit = 0.sp,             // extra space between letters if you want
) {
    val mergedStyle = style.merge(TextStyle(fontSize = fontSize))

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(letterSpacing.value.dp)
    ) {
        text.forEachIndexed { index, char ->
            val outlineColor = outlineColors[index % outlineColors.size]

            Box {
                // Outline
                Text(
                    text = char.toString(),
                    style = mergedStyle.copy(
                        color = outlineColor,
                        drawStyle = Stroke(
                            width = outlineWidth,
                            join = StrokeJoin.Round
                        )
                    )
                )
                // Fill
                Text(
                    text = char.toString(),
                    style = mergedStyle.copy(color = fillColor)
                )
            }
        }
    }
}