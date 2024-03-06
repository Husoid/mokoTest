package compose.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tisonline.core.MR
import compose.themes.ThemeColors
import compose.ui.splash.viewModel.models.SplashEvent
import compose.ui.splash.viewModel.models.SplashViewState
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import dev.icerock.moko.resources.getImageByFileName

@Composable
fun SplashView(state: SplashViewState, eventHandler: (SplashEvent) -> Unit) {
    //TODO Заменить тексты на строковые ресурсы
    Column(
        Modifier.fillMaxSize(),
        //verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(MR.images.car_black),
            modifier = Modifier.padding(top = 64.dp, start = 124.dp, end = 124.dp),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Box {
            Column {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ThemeColors.color.coralTis
                    ),
                    enabled = !state.isCliked,
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        eventHandler.invoke(SplashEvent.LoginClick)
                    }
                ) {
                    Text(
                        "Login Now", //color = Theme.colors.primaryTextColor,
                        fontSize = 16.sp, fontWeight = FontWeight.Bold
                    )
                }
               // Button()
            }
        }
        Text(
            modifier = Modifier.clickable {

            },
            text = stringResource(MR.strings.company_name),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Box {

        }
        Text(text = "Версия 1.0.0")
    }
}