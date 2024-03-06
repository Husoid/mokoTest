package compose.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors =
        when (darkTheme) {
            true -> baseDarkPalette
            false -> baseLightPalette
        }
    CompositionLocalProvider(
        LocalColors provides colors,
        content = content
    )
}

object ThemeColors {
    val color: TisColors
        @Composable
        get() = LocalColors.current
}