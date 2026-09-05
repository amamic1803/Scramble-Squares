package io.github.amamic1803.scramble_squares.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.imageResource
import scramble_squares.shared.generated.resources.Res
import scramble_squares.shared.generated.resources.wood_dark
import scramble_squares.shared.generated.resources.wood_light

@Composable
@Preview
fun Background(isDark: Boolean = isSystemInDarkTheme(), content: @Composable BoxScope.() -> Unit = {}) {
    val texture = if (isDark) imageResource(Res.drawable.wood_dark) else imageResource(Res.drawable.wood_light)
    val tiledBrush = remember(texture) {
        ShaderBrush(
            ImageShader(
                image = texture,
                tileModeX = TileMode.Repeated,
                tileModeY = TileMode.Repeated
            )
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .background(tiledBrush)
    ) {
        content()
    }
}
