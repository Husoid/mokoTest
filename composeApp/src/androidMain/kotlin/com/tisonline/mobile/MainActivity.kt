package com.tisonline.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.toArgb
import compose.themes.MainTheme
import compose.themes.ThemeColors
import navigation.generateGraph
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent
import ru.alexgladkov.odyssey.core.configuration.DisplayType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainTheme(
                darkTheme = false
            ) {
                val configuration = OdysseyConfiguration(
                    canvas = this,
                    displayType = DisplayType.EdgeToEdge,
                    backgroundColor = ThemeColors.color.primaryBackground,
                    navigationBarColor = ThemeColors.color.primaryBackground.toArgb()
                )

                setNavigationContent(
                    configuration,
                    onApplicationFinish = {
                        this.finishAffinity()
                    }
                ) {
                    generateGraph()
                }
            }
        }
    }
}