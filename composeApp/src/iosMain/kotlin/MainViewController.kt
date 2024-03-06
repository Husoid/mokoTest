import androidx.compose.ui.window.ComposeUIViewController
import compose.themes.MainTheme
import compose.themes.ThemeColors
import navigation.generateGraph
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent

fun MainViewController() = ComposeUIViewController {
    MainTheme(
        darkTheme = false
    ) {
        val configuration = OdysseyConfiguration(
            backgroundColor = ThemeColors.color.primaryBackground
        )

        setNavigationContent(configuration, onApplicationFinish = {
            println("Exit")
        }) {
            generateGraph()
        }
    }
}
