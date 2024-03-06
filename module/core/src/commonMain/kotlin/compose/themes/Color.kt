package compose.themes

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class TisColors(
    // основной цвет текста и элементов
    val primaryTextAndElement: Color,
    // основной серый цвет, фон
    val primaryBackground: Color,
    // зеленый
    val greenTis: Color,
    // серый, логотип ТИС
    val grayTis: Color,
    // синий
    val blueTis: Color,
    // коралловый
    val coralTis: Color,
    // желтый
    val yellowTis: Color,
    // цвет неактивных кнопок и текста
    val notActivElement: Color
)

val LocalColors = staticCompositionLocalOf<TisColors> {
    error("No colors provided")
}