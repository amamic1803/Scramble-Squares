package io.github.amamic1803.scramble_squares.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import scramble_squares.shared.generated.resources.Res
import scramble_squares.shared.generated.resources.atma_regular
import scramble_squares.shared.generated.resources.atma_semibold
import org.jetbrains.compose.resources.Font
import scramble_squares.shared.generated.resources.atma_bold
import scramble_squares.shared.generated.resources.atma_light
import scramble_squares.shared.generated.resources.atma_medium


private val AppTypography = Typography(
    titleMedium = TextStyle(
        fontWeight = FontWeight(500),
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)


@Composable
private fun atmaFontFamily(): FontFamily {
    return FontFamily(
        Font(
            resource = Res.font.atma_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.atma_light,
            weight = FontWeight.Light,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.atma_medium,
            weight = FontWeight.Medium,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.atma_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.atma_semibold,
            weight = FontWeight.SemiBold,
            style = FontStyle.Italic
        )
    )
}


@Composable
fun appTypography(): Typography {
    val atma = atmaFontFamily()

    return with(AppTypography) {
        copy(
            displayLarge = displayLarge.copy(fontFamily = atma),
            displayMedium = displayMedium.copy(fontFamily = atma),
            displaySmall = displaySmall.copy(fontFamily = atma),
            headlineLarge = headlineLarge.copy(fontFamily = atma),
            headlineMedium = headlineMedium.copy(fontFamily = atma),
            headlineSmall = headlineSmall.copy(fontFamily = atma),
            titleLarge = titleLarge.copy(fontFamily = atma),
            titleMedium = titleMedium.copy(fontFamily = atma),
            titleSmall = titleSmall.copy(fontFamily = atma),
            bodyLarge = bodyLarge.copy(fontFamily = atma),
            bodyMedium = bodyMedium.copy(fontFamily = atma),
            bodySmall = bodySmall.copy(fontFamily = atma),
            labelLarge = labelLarge.copy(fontFamily = atma),
            labelMedium = labelMedium.copy(fontFamily = atma),
            labelSmall = labelSmall.copy(fontFamily = atma),
        )
    }
}
